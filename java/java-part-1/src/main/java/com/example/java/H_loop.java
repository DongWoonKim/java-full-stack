package com.example.java;

import java.util.Scanner;

// * 반복문
// 어떤 작업이 반복적으로 수행되도록 할 때 사용된다.
public class H_loop {
    // * for문
    /*
        for ( 초기값; 조건식; 증감식 ) {
            // 조건식이 참일 때 수행될 문장들을 적는다.
        }
     */
    public static void exam1() {
        for ( int i = 0; i < 10; i++ ) {
            System.out.println("cnt : " + i);
        }
    }

    public static void exam2() {
        System.out.println("======== 구구단 2단 ========");
        for ( int i = 1; i < 10; ++i ) {
            System.out.println( "2 * " + i + " = " + (2 * i) );
        }
    }

    public static void exam3() {
        for ( int i = 0; i < 10; ++i ) {
            System.out.println("첫번째 for문 : " + i);
            for ( int j = 0; j < 10; ++j ) {
                System.out.println(j);
            }
        }
    }

    public static void exam4() {
        System.out.println("======== 구구단 전체 ========");
        for ( int i = 2; i < 10; ++i ) {
            System.out.println("[" + i + "단]");
            for ( int j = 1; j < 10; ++j ) {
                System.out.println( i + "*" + j + " = " + (i * j) );
            }
        }
    }

    public static void exam5() {
        System.out.println("===== 짝수만 출력하기 =====");
        for ( int i = 0; i < 10; ++i ) {
            if ( i % 2 != 0 ) continue;
            System.out.println(i);
        }
    }

    public static void exam6() {
        System.out.println("==== 원하는 조건에서 멈추기 ====");
        for ( int i = 0; i < 10; ++i ) {
            if ( i == 5 ) break;
            System.out.println(i);
        }
    }

    public static void exam7() {
        for ( int i = 0; i < 2; ++i ) {
            System.out.println("- 첫 번째 루프 : " + i);
            for ( int j = 0; j < 2; ++j ) {
                System.out.println("-- 두 번째 루프 : " + j);
                for ( int k = 0; k < 3; ++k ) {
                    System.out.println("--- 세 번째 루프 : " + k);
                }
            }
        }
    }

    public static void exam8() {
        int i = 0;
        for (; i < 10;) {
            System.out.println(i++);
        }
    }

    public static void exam9() {
        int i = 0;
        for (;;) {
            if ( i > 10 ) break;
            System.out.println(i++);
        }
    }

    public static void exam10() {
        // 무한 루프
        int i = 0;
        for (;true;) {
            System.out.println(i++);
        }
    }

    public static void exam11() {
        for ( int i = 9; i >= 0; i-- ) {
            System.out.println(i);
        }
    }

    // * while문
    /*
        while (조건식) {
            // 조건식의 연산결과가 참(true)인 동안, 반복될 문장을 적는다.
        }
     */
    public static void exam12() {
        int cnt = 0;
        while ( cnt == 10 ) {
            System.out.println(cnt);
            cnt++;
        }
    }

    public static void exam13() {
        System.out.println("==== 구구단 2단 ====");
        int j = 1;
        while (j < 10) {
            System.out.println("2 * " + j + " = " + (2 * j) );
            j++;
        }
    }

    public static void exam14() {
        System.out.println("==== 구구단 전체 ====");
        int i = 2;
        while ( i < 10 ) {
            System.out.println("[" + i + "단 ] ");
            int j = 1;
            while (j < 10) {
                System.out.println(i + "*" + j + " = " + (i * j) );
                j++;
            }
            i++;
        }
    }

    public static void exam15() {
        int i = 10;
        while ( true ) {
            if ( i == 0 ) break;
            System.out.println(i--);
        }
    }

    public static void exam16() {
        int i = 10;

        while ( --i > 0 ) {
            System.out.println(i);
        }
        System.out.println("=====================");
        i = 10;
        while ( i-- > 0 ) {
            System.out.println(i);
        }

    }

    public static void exam17() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("합을 구할 정수를 입력하시오.(만약 종료를 하려면 0을 누르시오.)");
            int num = sc.nextInt();
            if ( num == 0 ) break;

            int sum = 0;
            for ( int i =  1; i <= num; i++ ) {
                sum += i;
            }
            System.out.println("결과 : " + sum);

        }

        System.out.println("감사합니다.");

    }

    public static void exam18() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("원하는 구구단의 단을 입력하시오.(만약 종료를 하려면 0을 누르시오.)");
            int dan = sc.nextInt();
            if ( dan == 0 ) break;

            for ( int i =  1; i <= 10; i++ ) {
                System.out.println( dan + " * " + i + " = " + (dan * i) );
            }
        }

    }

    public static void exam19() {
        int i = 0;
        while ( i++ < 10 ) {
            if ( i % 2 != 0 ) continue;
            System.out.println(i);
        }
    }

    // * do-while문 : 최소한 한번은 수행될 것을 보장한다.
    /*
        do {
            // 조건식의 연산결과가 참일 때 수행될 문장들을 적는다.
        } while (조건식);
     */
    public static void exam20() {
        int i = 0;
        while ( i != 0 ) {
            System.out.println("while문 입니다.");
        }

        do {
            System.out.println("do-while문 입니다.");
        } while ( i != 0 );
    }

    public static void main(String[] args) {
        exam19();
    }

}
