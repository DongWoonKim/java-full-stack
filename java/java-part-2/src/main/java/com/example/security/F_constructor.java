package com.example.security;

// * 생성자(Constructor)
// 객체가 생성될 때 호출되는 특수한 메서드입니다.
// 생성자는 객체의 초기 상태를 설정하는 데 사용되며,
// 클래스 이름과 동일한 이름을 가져야 합니다.
// 생성자는 반환 타입이 없으며, 객체가 생성될 때 자동으로 호출됩니다.

// * 생성자의 주요 특징
//	1.	클래스 이름과 동일: 생성자는 클래스 이름과 동일한 이름을 가져야 합니다.
//	2.	반환 타입 없음: 생성자는 반환 타입을 명시하지 않습니다. 반환 타입을 명시하면 메서드로 인식됩니다.
//	3.	오버로딩 가능: 생성자는 메서드와 마찬가지로 오버로딩이 가능합니다. 즉, 매개변수의 개수나 타입이 다른 여러 개의 생성자를 정의할 수 있습니다.
//	4.	기본 생성자: 클래스에 생성자를 명시하지 않으면, 컴파일러가 매개변수가 없는 기본 생성자(default constructor)를 자동으로 추가합니다. 하지만 사용자가 하나라도 생성자를 정의하면, 컴파일러는 기본 생성자를 추가하지 않습니다.
//	5.	객체 초기화: 생성자는 객체의 필드를 초기화하는 데 사용됩니다.

// * 생성자의 종류
//	1.	기본 생성자 (Default Constructor):
//	•	매개변수가 없는 생성자입니다.
//	•	클래스에 생성자가 정의되지 않은 경우, 컴파일러가 자동으로 제공하는 생성자입니다.
//	•	클래스에서 별도로 생성자를 정의하지 않으면, 컴파일러는 자동으로 기본 생성자를 추가합니다.
//	2.	매개변수가 있는 생성자 (Parameterized Constructor):
//	•	객체를 생성할 때 인수를 전달받아 필드를 초기화하는 생성자입니다.
//	•	여러 개의 생성자를 오버로딩하여 다양한 초기화 방법을 제공할 수 있습니다.
//	3.	복사 생성자 (Copy Constructor):
//	•	동일한 클래스의 다른 객체를 복사하여 새로운 객체를 생성하는 생성자입니다.
//	•	자바에서는 기본적으로 제공되지는 않지만, 필요에 따라 개발자가 직접 구현할 수 있습니다.

// * 생성자의 역할과 이점
//	1.	객체 초기화: 생성자는 객체가 생성될 때 필요한 초기값을 설정하는 데 사용됩니다.
//	2.	코드의 가독성: 다양한 형태의 생성자를 제공함으로써, 객체를 직관적이고 간결하게 생성할 수 있습니다.
//	3.	오버로딩을 통한 유연성: 생성자 오버로딩을 통해, 다양한 상황에 맞춰 객체를 생성할 수 있는 유연성을 제공합니다.
//	4.	캡슐화: 생성자를 통해 필수적인 초기화가 강제되므로, 객체가 항상 유효한 상태로 생성되도록 할 수 있습니다.

// ** 생성자는 자바에서 객체 지향 프로그래밍의 중요한 구성 요소로,
// 객체의 일관성과 유효성을 유지하는 데 핵심적인 역할을 합니다.

public class F_constructor {
    public static void main(String[] args) {
        // 기본 생성자를 사용하여 객체 생성
        F_person1 person1 = new F_person1();
        person1.display();

        // 매개변수가 있는 생성자를 사용하여 객체 생성
        F_person2 person2 = new F_person2("Alice", 30);
        person2.display(); // Name: Alice, Age: 30

        // 기본 생성자를 사용하여 객체 생성
        F_person3 person3 = new F_person3();
        person3.display(); // Name: Unknown, Age: 0

        // 이름만 초기화하는 생성자를 사용하여 객체 생성
        F_person3 person4 = new F_person3("Bob");
        person4.display(); // Name: Bob, Age: 0

        // 이름과 나이를 초기화하는 생성자를 사용하여 객체 생성
        F_person3 person5 = new F_person3("Charlie", 25);
        person5.display(); // Name: Charlie, Age: 25

        // 원본 객체 생성
        F_person4 person6 = new F_person4("David", 40);

        // 복사 생성자를 사용하여 객체 복사
        F_person4 person7 = new F_person4(person6);

        person6.display(); // Name: David, Age: 40
        person7.display(); // Name: David, Age: 40
    }
}
