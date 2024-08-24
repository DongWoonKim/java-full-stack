package com.example.security;

public class F_person3 {
    String name;
    int age;

    // 기본 생성자
    public F_person3() {
        this.name = "Unknown";
        this.age = 0;
    }

    // 매개변수가 있는 생성자
    public F_person3(String name) {
        this.name = name;
        this.age = 0;
    }

    // 매개변수가 두 개인 생성자
    public F_person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
