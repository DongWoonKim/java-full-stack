package com.example.java;

public class F_person5 {

    String name;
    int age;

    public F_person5() {
//        System.out.println(""); // 반드시 첫 줄에 생성자 호출이 와야하므로 에러
//        F_person5("John", 13); // 에러 this로 호출해야됨.
        this("John", 13);
    }

    public F_person5(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
