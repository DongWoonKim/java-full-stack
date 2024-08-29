package com.example.java;

// *다형성(Polymorphism)
// 동일한 인터페이스나 부모 클래스를 공유하는 여러 객체들이 각자 다른 방식으로 동작하도록 할 수 있는 기능을 말한다.
// 다형성은 “하나의 인터페이스로 여러 형태의 객체를 처리할 수 있다”는 의미를 가지고 있다.
// 이를 통해 코드의 유연성과 확장성을 크게 향상시킬 수 있습니다.
class Animal {
    // 부모 클래스에서 정의된 메서드
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    // 자식 클래스에서 메서드를 오버라이딩
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }

    public void fetch() {
        System.out.println("Dog fetches a ball");
    }
}

class Cat extends Animal {
    // 자식 클래스에서 메서드를 오버라이딩
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}
public class K_polymorphism {
    public static void main(String[] args) {
        Animal myAnimal = new Animal(); // Animal 타입의 객체
        Animal myDog = new Dog();       // Dog 타입의 객체, Animal 타입으로 업캐스팅
        Animal myCat = new Cat();       // Cat 타입의 객체, Animal 타입으로 업캐스팅

        myAnimal.sound();  // "Animal makes a sound" 출력
        myDog.sound();     // "Dog barks" 출력 (런타임 시점에 Dog의 sound() 호출)
        myCat.sound();     // "Cat meows" 출력 (런타임 시점에 Cat의 sound() 호출)

        // 다운캐스팅을 통해 다시 Dog 타입으로 변환
        Dog myDog2 = (Dog) myAnimal;
        myDog2.fetch(); // "Dog fetches a ball" 출력
    }
}
