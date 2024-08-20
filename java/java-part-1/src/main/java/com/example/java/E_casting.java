package com.example.java;

// * 형변환
// 변수 또는 상수의 타입을 다른 타입으로 변환하는 것.
// 기본형에서 boolean을 제외한 나머지 타입들은 서로 형변환이 가능하다.

// * 암시적(자동) 형변환과 명시적(강제) 형변환이 있다.
// 암시적 형변환은 더 작은 자료형이 더 큰 자료형으로 변환될 때 자동으로 이루어지며,
// 명시적 형변환은 반대로 더 큰 자료형을 더 작은 자료형으로 변환할 때 명시적으로 해야 한다.

// * 암시적 형변환 순서 (작은 크기 → 큰 크기)
// 1.	byte → short → int → long → float → double
// 2.	char → int → long → float → double
// 이 순서대로 자료형이 자동으로 변환될 수 있으며, 변환 중 데이터 손실이 없거나 적은 경우에 암시적 형변환이 이루어진다.

public class E_casting {

    public static void main(String[] args) {

        double d = 3.14;
        System.out.println(d);
        int score = (int) d;
        System.out.println(score);

        // int -> char
        int n = 65;
        System.out.println(n);
        char ch = (char) n;
        System.out.println(ch);

        // char -> int
        char ch2 = 'A';
        System.out.println(ch2);
        int n2 = ch2;
        System.out.println(n2);

        // float -> int
        float f = 3.14f;
        System.out.println(f);
        int n3 = (int)f;
        System.out.println(n3);

        // int -> float
        int n4 = 3;
        System.out.println(n4);
        float f4 = n4;
        System.out.println(f4);

        // 암시적 형변환
        byte b = 10;
        int i = b;  // 암시적 형변환: byte → int

        char c = 'A';
        int j = c;  // 암시적 형변환: char → int

        int k = 100;
        double dd = k;  // 암시적 형변환: int → double

        // 명시적 형변환
        int i2 = 100;
        byte b2 = (byte) i;  // 명시적 형변환: int → byte

        double ddd = 9.78;
        int j2 = (int) d;  // 명시적 형변환: double → int

    }

}
