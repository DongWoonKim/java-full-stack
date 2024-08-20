package com.example.java;

// * 변수 : 값을 저장활 수 있는 메모리상의 공간
// 변수 타입 : 변수에 저장될 값이 어떤 타입인지를 지정하는 것.
// 변수 이름 : 변수에 붙인 이름.
// 변수 초기화 : 변수를 사용하기 전에 처음으로 값을 저장하는 것.

// * 변수의 명명 규칙
// 1. 대소문자가 구분되며 길이에 제한이 없다.
// 2. 예약어를 사용해서는 안된다. ex) true, false, break, int, for,...
// 3. 숫자로 시작하면 안된다. ex) int 12ab;
// 4. 특수문자 '_'와 '$'만을 허용한다.

// * 변수의 타입
// 기본형(Primitive type)과 참조형(Reference type)
// 자료형은 크게 '기본형'과 '참조형' 두 가지로 나눌 수 있는데, 기본형 변수는 실제 값(data)을 저장하는 반면,
// 참조형 변수는 어떤 값이 저장되어 있는 주소(memory address)를 값으로 갖는다.
// - 기본형 : 논리형(boolean), 문자형(char), 정수형(byte, short, int, long), 실수형(float, double)
// - 참조형 : 객체의 주소를 저장한다. 기본형을 제외한 나머지 타입.

// ** 기본형
// - 논리형 : boolean
// true와 false 중 하나를 값으로 갖으며, 조건식과 논리적 계산에 사용된다.
// 크기 : 1byte
// 범위 : -
// - 문자형 : char
// 문자를 저장하는데 사용되며, 하나의 문자만 저장할 수 있다.
// 크기 : 2byte
// 범위 : ‘\u0000’ ~ ‘\uffff’
// - 정수형 : byte, short, int, long
// 정수를 저장하는데 사용된다. byte는 이진데이터를 다룰 때, short은 C언어와의 호환을 위해 추가되었다.
// 크기 : byte(1byte-8bit), short(2byte), int(4byte), long(8byte)
// 범위 : byte(-128 ~ 127), short(-32,768 ~ 32,767), int(-2,147,483,648 ~ 2,147,483,647 -> 약 20억), long(-9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807)
// - 실수형 : float, double
// 실수를 저장하는데 사용된다.
// 크기 : float(4byte), double(8byte)
// 범위 : float(1.4E-45 ~ 3.4028235E38 (정밀도 약 6~7자리)), double(4.9E-324 ~ 1.7976931348623157E308 (정밀도 약 15~16자리))

// * 상수(Constant) : 변수와 마찬가지로 '값을 저장할 수 있는 공간'이지만, 변수와 달리 한번 값을 저장하면 다른 값으로 변경할 수 없다.
// 변수 타입 앞에 'final'을 붙여주면 된다.

// * 리터럴(Literal) : 변수에 담긴 값을 리터럴이라고 부른다.

public class B_variable_method {

    public static void exam1() {
        // 변수
        byte myByte = 127;
        System.out.println(myByte);

        short myShort = 127;
        System.out.println(myShort);

        int age = 25;
        System.out.println(age);

        int num1 = 100;
        System.out.println(num1);
        num1 = 200;
        System.out.println(num1);

        long myLong = 127L;
        System.out.println(myLong);

        char a = 'a';
        System.out.println(a);
        a = 66;
        System.out.println(a);

        boolean flag = true;
        System.out.println(flag);
        flag = false;
        System.out.println(flag);

        // 상수
        final float PI = 3.14f;
        final double LONG_PI = 3.1415926535;
        System.out.println(PI);
        System.out.println(LONG_PI);

        // 문자열
        String myStr = "Hello World!";
        System.out.println(myStr);
    }

    // * 함수 : 프로그래밍에서 특정 작업을 수행하기 위해 작성된 코드의 묶음
    /*
        1. 함수 선언 : 함수의 이름과 특성을 정의하는 부분이다.

        2. 매개변수(Parameters) : 매개변수는 함수가 작업을 수행하는 데 필요한 입력값을 전달받는 부분이다.

        3. 반환 타입(Return Type) : 함수가 어떤 유형의 값을 반환할지를 정의.
     */
    public static int add(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    // * return
    // 1. 값을 반환 : 함수가 어떤 값을 계산하거나 처리한 후, 그 결과를 호출한 코드로 돌려줄 때 사용된다.
    // 2. 함수 종료 : return 문이 실행되면, 해당 함수는 즉시 종료된다.
    public static void printResult(int result) {
        System.out.println(result);
        return;
    }



    public static void main(String[] args) {


    }
}
