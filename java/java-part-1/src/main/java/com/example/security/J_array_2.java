package com.example.security;

// * 다차원 배열
// * 2차원 배열
// 타입[][] 변수이름;

public class J_array_2 {

    public static void exam1() {
        int[][] score = new int[2][2];
        score[0][0] = 1;
        score[0][1] = 2;
        score[1][0] = 3;
        score[1][1] = 4;

        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++) {
                System.out.print(score[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void exam2() {
        int[][] score = { { 1, 2 }, { 3, 4 } };
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++) {
                System.out.print(score[i][j] + " ");
            }
            System.out.println();
        }
    }

    // * 가변 배열
    // 2차원 이상의 다차원 배열을 생성할 때 전체 배열 차수 중 마지막 차수의 길이를 지정하지 않고,
    // 추후에 각기 다른 길이의 배열을 생성함으로써 고정된 형태가 아닌 보다 유동적인 가변 배열을 구성할 수 있다.
    public static void exam3() {
        int[][] score = new int[2][];

        score[0] = new int[] { 1, 2 };
        score[1] = new int[3];

        score[1][0] = 1;
        score[1][1] = 2;
        score[1][2] = 3;

        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++) {
                System.out.print(score[i][j] + " ");
            }
            System.out.println();
        }
    }

    // * 3차원 배열
    public static void exam4() {
        int[][][] score = new int[2][2][2];
        int[][][] score2 = new int[2][2][];
    }

    public static void main(String[] args) {
        exam1();
    }

}
