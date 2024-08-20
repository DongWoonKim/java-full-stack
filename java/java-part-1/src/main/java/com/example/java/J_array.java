package com.example.java;

// * 배열
// 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것.
// 변수와 달리 배열은 각 저장공간이 연속적으로 배치되어 있다.

// * 배열의 생성
// 타입[] 변수이름 = new 타입[길이];


public class J_array {

    public static void exam1() {
        int[] score = new int[3];

        score[0] = 10;
        score[1] = 20;
        score[2] = 30;

        System.out.println(score);
        System.out.println(score[0]);
        System.out.println(score[1]);
        System.out.println(score[2]);

    }

    public static void exam2() {
        int[] score = {10, 20, 30};
        System.out.println(score.length);
        for (int i = 0; i < score.length; i++) {
            System.out.println(score[i]);
        }
    }

    public static void exam3() {
        int[] score = new int[3];

        for (int i = 1; i <= score.length; i++) {
            score[i-1] = i * 10;
        }

        for ( int s : score ) {
            System.out.println(s);
        }

    }

    public static void exam4() {
        char[] words = { 'h', 'e', 'l', 'l', 'o' };
        for (char c : words) {
            System.out.println(c);
        }
    }

    public static void exam5() {
        String[] words = { "hello", "worlds"};
        for (String s : words) {
            System.out.println(s);
        }
    }

    public static void exam6(int[] score, int num) {
        score[1] = 90;
        num = 90;
    }

    public static void exam6_1() {
        int[] score = {10, 20, 30};
        int num = 20;

        for (int i = 0; i < score.length; i++) {
            System.out.println(score[i]);
        }
        System.out.println("======");
        exam6(score, num);
        for (int i = 0; i < score.length; i++) {
            System.out.println(score[i]);
        }
        System.out.println("======");
        System.out.println(num);
    }

    public static void main(String[] args) {

    }
}
