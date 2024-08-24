package com.example.java;

// * String 클래스의 특징
//	•	불변성(Immutable)
//	String 객체가 생성되면 그 객체의 문자열 값은 변경할 수 없습니다.
//	문자열을 수정하려면 새로운 String 객체를 생성해야 합니다.
//	•	메모리 효율성
// 같은 값을 가진 String 리터럴은 메모리에서 공유됩니다. 이는 메모리 사용을 줄이고, 성능을 향상시킵니다.

public class L_String {

    // 1. charAt(int index)
    //	•	설명: 문자열에서 특정 위치에 있는 문자(char)를 반환합니다.
    //	•	활용: 문자열의 각 문자를 순회하거나 특정 위치의 문자를 비교할 때 유용합니다.
    public static void exam1() {
        String str = "hello";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 각 문자에 대해 어떤 작업을 수행
            System.out.println(c);
        }
    }

    // 2. substring(int beginIndex, int endIndex)
    //	•	설명: 문자열의 특정 부분을 추출하여 반환합니다.
    //	•	활용: 문자열의 일부를 분리하거나, 특정 패턴을 추출할 때 사용합니다.
    public static void exam2() {
        String str = "algorithm";
        String sub = str.substring(0, 5); // "algor"
        System.out.println(sub);
    }

    // 3. split(String regex)
    //	•	설명: 주어진 정규 표현식을 기준으로 문자열을 분리하여 배열로 반환합니다.
    //	•	활용: 문자열을 특정 구분자로 분리하여 여러 부분으로 나눌 때 유용합니다. 특히 CSV 데이터 처리, 공백으로 단어를 분리할 때 많이 사용됩니다.
    public static void exam3() {
        String str = "one,two,three";
        String[] parts = str.split(","); // {"one", "two", "three"}
        for (String part : parts) {
            System.out.println(part);
        }
    }

    // 4. toCharArray()
    //	•	설명: 문자열을 문자 배열로 변환합니다.
    //	•	활용: 문자열을 배열처럼 다루고 싶을 때 유용하며, 특히 for-each 문을 통해 문자열의 각 문자를 쉽게 순회할 수 있습니다.
    public static void exam4() {
        String str = "hello";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            // 각 문자에 대해 작업 수행
            System.out.println(c);
        }
    }

    // 5. equals(String anotherString)
    //	•	설명: 두 문자열의 값을 비교하여 같으면 true, 다르면 false를 반환합니다.
    //	•	활용: 문자열 비교에서 자주 사용됩니다. 예를 들어, 회문(palindrome) 여부를 판단할 때 유용합니다.
    public static void exam5() {
        String str1 = "abc";
        String str2 = "abc";
        System.out.println( str1.equals(str2) );
    }

    // 6. contains(CharSequence s)
    //	•	설명: 문자열이 특정 문자열을 포함하고 있는지 확인합니다.
    //	•	활용: 문자열 내 특정 패턴이나 부분 문자열이 존재하는지 확인할 때 사용됩니다.
    public static void exam6() {
        String str = "hello world";
        System.out.println(str.contains("world"));
    }

    // 7. replace(char oldChar, char newChar) 및 replace(CharSequence target, CharSequence replacement)
    //	•	설명: 문자열 내의 특정 문자 또는 문자열을 다른 문자 또는 문자열로 대체합니다.
    //	•	활용: 문자열 변환 문제나 패턴 치환 문제에서 유용합니다.
    public static void exam7() {
        String str = "hello";
        String newStr = str.replace('l', 'p'); // "heppo"
    }

    // 8. indexOf(String str) 및 lastIndexOf(String str)
    //	•	설명: 특정 문자열이 처음 또는 마지막으로 나타나는 위치를 반환합니다.
    //	•	활용: 특정 패턴이나 부분 문자열의 위치를 찾을 때 사용됩니다.
    public static void exam8() {
        String str = "hello";
        int index = str.indexOf("l"); // 2
        int lastIndex = str.lastIndexOf("l"); // 3
        System.out.println(index + "," + lastIndex);
    }

    // 9. StringBuilder 및 StringBuffer
    //	•	설명: StringBuilder와 StringBuffer는 가변(mutable) 문자열을 다루기 위한 클래스입니다. StringBuilder는 성능이 우수하며, StringBuffer는 스레드 안전(thread-safe)한 버전입니다.
    //	•	활용: 반복적인 문자열 수정이 필요한 문제에서 성능을 최적화하기 위해 사용됩니다.
    public static void exam9() {
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        sb.append(" world");
        String result = sb.toString(); // "hello world"
        System.out.println(result);
    }

    // 10. reverse()
    //	•	설명: 문자열을 뒤집습니다. String 자체에는 reverse() 메서드가 없지만, StringBuilder를 사용하여 문자열을 뒤집을 수 있습니다.
    //	•	활용: 회문(palindrome) 문제나 문자열을 뒤집는 문제에서 자주 사용됩니다.
    public static void exam10() {
        String str = "algorithm";
        String reversed = new StringBuilder(str).reverse().toString(); // "mhtirogla"
        System.out.println(reversed);
    }

    // 11. compareTo(String anotherString)
    //	•	설명: 두 문자열을 사전적으로(lexicographically) 비교하여, 현재 문자열이 더 작으면 음수, 같으면 0, 더 크면 양수를 반환합니다.
    //	•	활용: 문자열 정렬, 사전 순 비교, 순위 매기기 등에서 유용합니다.
    public static void exam11() {
        String str1 = "apple";
        String str2 = "banana";
        int result = str1.compareTo(str2); // 음수 (str1이 사전적으로 더 작음)
        System.out.println(result);
    }

    // 12. toLowerCase() 및 toUpperCase()
    //	•	설명: 문자열을 소문자 또는 대문자로 변환합니다.
    //	•	활용: 대소문자 구분 없이 문자열을 비교하거나 특정 형식으로 변환할 때 사용됩니다.
    public static void exam12() {
        String str = "Hello World";
        String lower = str.toLowerCase(); // "hello world"
        String upper = str.toUpperCase(); // "HELLO WORLD"
        System.out.println(lower);
        System.out.println(upper);
    }

    public static void main(String[] args) {
    }
}
