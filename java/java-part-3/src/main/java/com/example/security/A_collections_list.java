package com.example.security;

// * List
// 순서가 있는 요소들의 컬렉션을 나타내는 인터페이스이다다.
// List 인터페이스를 구현하는 대표적인 클래스에는 ArrayList, LinkedList, Vector 등이 있다.
// List는 중복된 요소를 허용하며, 인덱스를 기반으로 요소에 접근할 수 있다.

// * 주요 특징
//	•	순서 유지: List는 요소들이 추가된 순서를 유지한다.
//	•	인덱스로 접근: 각 요소는 인덱스를 통해 접근할 수 있다. 인덱스는 0부터 시작한다.
//	•	중복 허용: 동일한 값을 가진 요소가 여러 개 있을 수 있다.
//	•	유연한 크기: 구현체는 동적으로 크기를 조절할 수 있다.

// * 주요 메소드
//	•	add(E e): 리스트에 요소를 추가합니다.
//	•	get(int index): 인덱스에 있는 요소를 반환합니다.
//	•	remove(int index): 인덱스에 있는 요소를 제거합니다.
//	•	size(): 리스트의 요소 개수를 반환합니다.
//	•	contains(Object o): 리스트에 특정 요소가 포함되어 있는지 확인합니다.
//	•	clear(): 리스트의 모든 요소를 제거합니다.

// * 요약
//	•	ArrayList는 배열 기반의 리스트로, 인덱스를 통한 빠른 접근이 가능하지만 중간 삽입/삭제가 느립니다.
//	•	LinkedList는 노드 기반의 리스트로, 삽입/삭제가 빠르지만 인덱스를 통한 접근이 느립니다.
//	•	Stack은 후입선출(LIFO) 구조를 가지며, 요소를 스택에 추가하고 제거하는 데 사용됩니다.

import java.util.*;

public class A_collections_list {

    // 1. ArrayList
    // 설명
    //	•	배열 기반: ArrayList는 내부적으로 동적 배열을 사용하여 요소를 저장한다.
    //	•	빠른 접근: 인덱스를 통한 요소 접근이 매우 빠릅니다(시간 복잡도 O(1)).
    //	•	느린 삽입/삭제: 중간에 요소를 삽입하거나 삭제하는 경우, 해당 위치 이후의 모든 요소를 이동해야 하므로 성능이 떨어진다(시간 복잡도 O(n)).
    //	•	동적 크기 조절: 초기 용량이 가득 차면 자동으로 크기를 증가시킵니다.
    public void exam1() {
        // List 인터페이스를 구현한 ArrayList 생성
        List<String> list = new ArrayList<>();

        // 요소 추가
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // 특정 인덱스에 요소 추가
        list.add(1, "Grape");

        // 리스트의 크기 확인
        System.out.println("List size: " + list.size());  // 출력: 4

        // 인덱스를 사용하여 요소 접근
        System.out.println("Element at index 2: " + list.get(2));  // 출력: Banana

        // 요소 제거
        list.remove(3);

        // 리스트의 모든 요소 출력
        System.out.println("List elements: " + list);

        // 특정 요소가 리스트에 포함되어 있는지 확인
        if (list.contains("Apple")) {
            System.out.println("Apple is in the list.");
        }

        // 리스트의 모든 요소 제거
        list.clear();
        System.out.println("List after clearing: " + list);  // 출력: []

        // 순회 방법 1: for 루프 사용
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Element at index " + i + ": " + list.get(i));
        }

        // 순회 방법 2: 향상된 for 루프 사용
        for (String element : list) {
            System.out.println("Element: " + element);
        }

        // 순회 방법 3: Iterator 사용
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element: " + element);
        }

        // 순회 방법 4: ListIterator 사용 (양방향 순회 가능)
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String element = listIterator.next();
            System.out.println("Element: " + element);
        }

        // 역방향 순회
        while (listIterator.hasPrevious()) {
            String element = listIterator.previous();
            System.out.println("Element in reverse: " + element);
        }
    }

    // 2. LinkedList
    // 설명
    //	•	노드 기반: LinkedList는 이중 연결 리스트를 사용하여 요소를 저장한다.
    //	•	느린 접근: 인덱스를 통한 접근이 느리다(시간 복잡도 O(n)).
    //	•	빠른 삽입/삭제: 리스트의 앞이나 중간에 요소를 삽입하거나 삭제하는 것이 빠르다(시간 복잡도 O(1)).
    //	•	이중 연결: 각 노드는 이전 노드와 다음 노드에 대한 참조를 가지고 있다.
    public void exam2() {
        List<String> linkedList = new LinkedList<>();

        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Orange");

        System.out.println("LinkedList elements: " + linkedList);

        linkedList.add(1, "Grape");
        System.out.println("After adding Grape at index 1: " + linkedList);

        linkedList.removeLast();
        System.out.println("After removing last element: " + linkedList);

        String element = linkedList.get(0);
        System.out.println("Element at index 0: " + element);

        // 순회 방법 1: for 루프 사용
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("Element at index " + i + ": " + linkedList.get(i));
        }

        // 순회 방법 2: 향상된 for 루프 사용
        for (String ele : linkedList) {
            System.out.println("Element: " + ele);
        }

        // 순회 방법 3: Iterator 사용
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String ele = iterator.next();
            System.out.println("Element: " + ele);
        }

        // 순회 방법 4: ListIterator 사용 (양방향 순회 가능)
        ListIterator<String> listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            String ele = listIterator.next();
            System.out.println("Element: " + ele);
        }

        // 역방향 순회
        while (listIterator.hasPrevious()) {
            String ele = listIterator.previous();
            System.out.println("Element in reverse: " + ele);
        }

    }

    // 3. Stack
    // 설명
    //	•	LIFO 구조: Stack은 Last-In-First-Out(후입선출) 구조를 따른다.
    //	•	Vector 기반: Stack은 Vector 클래스를 상속받아 구현되었다.
    //	•	주요 메소드: push()(요소 삽입), pop()(요소 제거), peek()(맨 위의 요소를 확인), empty()(스택이 비어 있는지 확인) 등의 메소드가 있다.
    public void exam3() {
        Stack<String> stack = new Stack<>();

        stack.push("Apple");
        stack.push("Banana");
        stack.push("Orange");

        System.out.println("Stack elements: " + stack);

        String topElement = stack.pop();
        System.out.println("Popped element: " + topElement);
        System.out.println("Stack after pop: " + stack);

        String peekElement = stack.peek();
        System.out.println("Top element after peek: " + peekElement);

        System.out.println("Is stack empty? " + stack.empty());

        // 순회 방법 1: for 루프 사용
        for (int i = 0; i < stack.size(); i++) {
            System.out.println("Element at index " + i + ": " + stack.get(i));
        }

        // 순회 방법 2: 향상된 for 루프 사용
        for (String element : stack) {
            System.out.println("Element: " + element);
        }

        // 순회 방법 3: Iterator 사용
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element: " + element);
        }

        // 순회 방법 4: ListIterator 사용 (양방향 순회 가능)
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            String element = listIterator.next();
            System.out.println("Element: " + element);
        }

        // 역방향 순회
        while (listIterator.hasPrevious()) {
            String element = listIterator.previous();
            System.out.println("Element in reverse: " + element);
        }

        // 순회 방법 5: pop()을 사용한 순회 (스택의 특성 활용)
        while (!stack.isEmpty()) {
            String element = stack.pop();
            System.out.println("Popped element: " + element);
        }
    }

    public static void main(String[] args) {
    }
}