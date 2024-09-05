package com.example.java;

// * 기아 현상 (Starvation)
// 멀티스레딩 프로그래밍에서 특정 스레드가 다른 스레드들에 의해 지속적으로 자원을 얻지 못해 실행되지 못하는 상황을 의미한다.
// 이는 자원이 고르게 분배되지 않거나 우선순위가 낮은 스레드가 계속해서 우선순위가 높은 스레드에게 밀리는 경우 발생할 수 있다.
// 이 문제를 해결하는 방법 중 하나로, notifyAll()을 사용할 수 있다.
// notifyAll()은 대기 중인 모든 스레드를 깨워서 자원을 경쟁하도록 만든다.
// 반면에 notify()는 대기 중인 스레드 중 하나만 깨우기 때문에 기아 현상이 발생할 가능성이 더 크다.
// 아래는 기아 현상을 보여주는 간단한 예제입니다.

// 스레드들 간에 자원을 고르게 배분하지 않고 notify()만 사용하여 특정 스레드가 자원을 계속 얻지 못하는 상황을 시뮬레이션하고,
// notifyAll()을 사용해 기아 현상을 해결하는 방법을 설명한다.

class SharedResource {
    private boolean isAvailable = false;

    // 자원을 대기하는 메서드
    public synchronized void waitForResource(String threadName) {
        while (!isAvailable) {
            try {
                System.out.println(threadName + " is waiting for resource...");
                wait(); // 자원이 사용 가능할 때까지 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName + " got the resource!");
        isAvailable = false;  // 자원을 사용한 후에는 다시 unavailable 상태로
    }

    // 자원을 공급하는 메서드
    public synchronized void makeResourceAvailable() {
        isAvailable = true;
        System.out.println("Resource is now available!");
//        notify();
        notifyAll();  // 모든 대기 중인 스레드를 깨운다.
    }
}

class WorkerThread extends Thread {
    private SharedResource resource;
    private String threadName;

    public WorkerThread(SharedResource resource, String threadName) {
        this.resource = resource;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            resource.waitForResource(threadName);
            try {
                Thread.sleep(1000);  // 자원을 얻은 후 1초간 작업 수행
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class C_thread_8_3_notifyAll {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // 3개의 Worker 스레드 생성
        WorkerThread t1 = new WorkerThread(resource, "Thread 1");
        WorkerThread t2 = new WorkerThread(resource, "Thread 2");
        WorkerThread t3 = new WorkerThread(resource, "Thread 3");

        t1.start();
        t2.start();
        t3.start();

        // 2초마다 자원을 공급
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    resource.makeResourceAvailable();  // 자원을 공급
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 기아 현상 해결:
        //	•	이 예제에서 **notifyAll()**을 사용했기 때문에 대기 중인 모든 스레드가 자원을 공정하게 얻을 수 있는 기회를 갖게 됩니다.
        //	즉, 한 스레드가 자원을 독점하지 않으며, 일정한 간격으로 모든 스레드가 자원을 얻어 작업을 수행할 수 있습니다.
        //	•	만약 **notify()**를 사용하면 특정 스레드가 계속 자원을 얻지 못하고 대기 상태에 빠질 가능성이 생깁니다.
        //	예를 들어, Thread 1이 계속해서 자원을 얻고 Thread 2와 Thread 3가 기아 상태에 빠질 수 있습니다.
    }
}
