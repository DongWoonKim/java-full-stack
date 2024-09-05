package com.example.java;

// * 스레드의 동기화
// 싱글스레드 프로세스의 경우 프로세스 내에서 단 하나의 스레드만 작업하기 때문에
// 프로세스의 자원을 가지고 작업하는데 별문제가 없지만, 멀티스레드 프로세스의 경우
// 여러 스레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게 된다.
// 이러한 일이 발생하는 것 방지하기 위해서 한 스레드가 특정 작업을 끝마치기 전까지
// 다른 스레드에 의해 방해받지 않도록 하는 것이 필요하다.
// 그래서 도입된 개념이 바로 "임계 영역(critical section)"과 "잠금(lock)"이다.

// 공유 데이터를 사용하는 코드 영역을 임계 영역으로 지정해놓고, 공유 데이터(객체)가 가지고 있는
// lock을 획득한 단 하나의 스레드만 이 영역 내의 코드를 수행할 수 있게 한다.
// 그리고 해당 스레드가 임계 영역 내의 모든 코드를 수행하고 벗어나서 lock을 반납해야만
// 다른 스레드가 반납된 lock을 획득하여 임계 영역의 코드를 수행할 수 있게 된다.
// 이처럼 한 스레드가 진행 중인 작업을 다른 스레드가 간섭하지 못하도록 막는 것을 '스레드의 동기화(synchronization)'라고 한다.

// * synchronized를 이용한 동기화
// 1. 메서드 전체를 임계 영역으로 지정
// -> 메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가
// 메서드가 종료되면 lock을 반환한다.
/*
    public synchronized void calcSum() {
        // 임계 영역
    }
 */
// 2. 특정한 영역을 임계 영역으로 지정
// -> 메서드 내의 코드 일부를 블럭{}으로 감싸고 블럭 앞에 synchronized(참조변수) 를 붙이는 것인데,
// 이때 참조변수는 락을 걸고자하는 개체를 참조하는 것이어야 한다.
/*
    synchronized(객체의 참조변수) {
        // 임계 영역
    }
 */

// 임계 영역은 멀티스레드 프로그램의 성능을 좌우하기 때문에
// 가능하면 메서드 전체에 락을 거는 것보다 synchonized블럭으로 임계 영역을 최소화해서
// 보다 효율적인 프로그램이 되도록하는 것이 좋다.

class ThreadEx_account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

//    public void withdraw(int money) {
//        if (balance >= money) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            balance -= money;
//        }
//    }

    public void withdraw(int money) {

        synchronized (this) {
            if (balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                balance -= money;
            }
        }

    }
}

class ThreadEx_bank implements Runnable {

    private ThreadEx_account acc = new ThreadEx_account();

    @Override
    public void run() {
        while(acc.getBalance() > 0) {
            // 100, 200, 300 중의 한 값을 임의로 선택해서 출금
            int money = (int) (Math.random() * 3 + 1)* 100;
            acc.withdraw(money);
            System.out.println("balance: " + acc.getBalance());
        }
    }
}

public class C_thread_8_1_synchronized {
    // - synchronized
    public static void main(String[] args) {
        ThreadEx_bank r = new ThreadEx_bank();
        new Thread(r).start();
        new Thread(r).start();

        // 실행 결과를 보면 잔고(balance)가 음수인 것을 볼 수 있다.
        // 그 이유는 한 스레드가 if문의 조건식을 통과하고 출금하기 바로 직전에 다른 스레드가 끼어들어서 출금을 먼저 했기 때문.

    }
}
