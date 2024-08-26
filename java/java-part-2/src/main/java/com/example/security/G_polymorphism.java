package com.example.security;

// * 다형성(Polymorphism)
// 객체 지향 프로그래밍의 중요한 개념 중 하나로,
// 같은 인터페이스 또는 부모 클래스를 공유하는 여러 자식 클래스들이 다양한 방식으로 동작할 수 있는 능력을 의미합니다.
// 다형성은 코드의 유연성과 재사용성을 크게 향상시키며, 특히 상속과 인터페이스를 통해 구현됩니다.

// * 다형성의 주요 특징
//	1.	코드의 유연성:
//	•	다형성을 통해 하나의 객체가 여러 타입의 참조 변수로 다뤄질 수 있습니다.
//	이는 프로그램이 더 유연하게 동작하고, 새로운 기능을 추가하기 쉽게 만듭니다.
//	2.	상속(Inheritance):
//	•	다형성은 상속을 통해 구현됩니다.
//	부모 클래스 타입의 참조 변수가 자식 클래스 객체를 참조할 수 있습니다.
//	3.	인터페이스(Interface):
//	•	인터페이스를 통해 다형성을 구현할 수 있습니다.
//	여러 클래스가 동일한 인터페이스를 구현함으로써, 인터페이스 타입의 참조 변수로 다양한 객체를 다룰 수 있습니다.
//	4.	메서드 오버라이딩(Method Overriding):
//	•	다형성은 주로 메서드 오버라이딩을 통해 나타납니다.
//	부모 클래스에 정의된 메서드를 자식 클래스에서 재정의하여 다양한 방식으로 동작하도록 만들 수 있습니다.

// 부모 클래스
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

// 자식 클래스
class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}

// 인터페이스
interface Shape {
    void draw();
}

// 구현 클래스
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

public class G_polymorphism {
    public static void main(String[] args) {
        Animal myAnimal = new Animal(); // 부모 클래스 객체
        Animal myDog = new Dog();       // 자식 클래스 객체
        Animal myCat = new Cat();       // 자식 클래스 객체

        myAnimal.sound();  // "Animal makes a sound"
        myDog.sound();     // "Dog barks"
        myCat.sound();     // "Cat meows"

        Shape myCircle = new Circle();
        Shape myRectangle = new Rectangle();

        myCircle.draw();    // "Drawing a Circle"
        myRectangle.draw(); // "Drawing a Rectangle"
    }
}
