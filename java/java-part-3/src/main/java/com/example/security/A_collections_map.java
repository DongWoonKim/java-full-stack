package com.example.security;

// * Map
// 키와 값의 쌍을 저장하는 자료구조를 나타낸다.
// Map은 중복된 키를 허용하지 않으며, 각 키는 하나의 값에 매핑된다.
// Map 인터페이스를 구현하는 대표적인 클래스에는 HashMap, TreeMap, LinkedHashMap 등이 있다.

// * 주요 특징
//	•	키-값 쌍: Map은 각 요소를 키와 값의 쌍으로 저장한다.
//	•	중복된 키 허용 안함: 동일한 키를 두 번 추가할 수 없다. 만약 동일한 키를 다시 추가하면 기존의 값이 덮어씌워진다.
//	•	빠른 검색: 특정 키를 사용하여 값에 빠르게 접근할 수 있다.

// * 주요 메소드
//	•	put(K key, V value): 맵에 키와 값을 추가한다. 만약 동일한 키가 이미 존재한다면 해당 키의 값이 새 값으로 대체된다.
//	•	get(Object key): 키에 대응하는 값을 반환한다. 해당 키가 존재하지 않으면 null을 반환한다.
//	•	remove(Object key): 특정 키에 대응하는 키-값 쌍을 제거한다.
//	•	containsKey(Object key): 맵에 특정 키가 포함되어 있는지 확인한다.
//	•	containsValue(Object value): 맵에 특정 값이 포함되어 있는지 확인한다.
//	•	size(): 맵에 저장된 키-값 쌍의 수를 반환한다.
//	•	clear(): 맵의 모든 요소를 제거한다.

// * 요약
//	•	HashMap은 빠른 검색과 삽입 성능을 제공하며, 요소의 순서를 보장하지 않습니다.
//	•	TreeMap은 키를 기준으로 정렬된 순서를 유지하며, 레드-블랙 트리 구조로 구성되어 있습니다.
// ** 레드-블랙 트리(Red-Black Tree)는 이진 검색 트리(Binary Search Tree)의 일종으로,
// 삽입, 삭제 등의 연산 후에도 트리의 균형을 유지하도록 설계된 자가 균형 이진 트리(Self-balancing Binary Search Tree)이다.
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class A_collections_map {

    // 1. HashMap
    // 설명
    //	•	해시 기반: HashMap은 내부적으로 해시 테이블을 사용하여 데이터를 저장한다.
    //	•	빠른 성능: 일반적으로 키를 통한 검색, 삽입, 삭제 작업이 O(1)의 시간 복잡도를 가진다.
    //	•	순서 보장 없음: 요소들이 저장된 순서를 보장하지 않는다.
    public void exam1() {
        Map<String, Integer> hashMap = new HashMap<>();

        // 요소 추가
        hashMap.put("Apple", 100);
        hashMap.put("Banana", 200);
        hashMap.put("Orange", 300);

        System.out.println("HashMap elements: " + hashMap);

        // 특정 키의 값 얻기
        int value = hashMap.get("Banana");
        System.out.println("Value for key 'Banana': " + value);

        // 요소 제거
        hashMap.remove("Orange");
        System.out.println("After removing 'Orange': " + hashMap);

        // 키 존재 여부 확인
        boolean containsKey = hashMap.containsKey("Apple");
        System.out.println("Does HashMap contain key 'Apple'? " + containsKey);

        // 순회 방법 1: entrySet() 사용
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 순회 방법 2: keySet() 사용
        for (String key : hashMap.keySet()) {
            Integer val = hashMap.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }

        // 순회 방법 3: values() 사용
        for (Integer val : hashMap.values()) {
            System.out.println("Value: " + value);
        }
    }

    // 2. TreeMap
    // 설명
    //	•	이진 검색 트리 기반: TreeMap은 내부적으로 레드-블랙 트리 구조를 사용하여 데이터를 저장합니다.
    //	•	정렬된 순서: 키에 따라 요소들이 자동으로 정렬됩니다(기본적으로 오름차순).
    //	•	성능: 삽입, 삭제, 검색 작업이 O(log n)의 시간 복잡도를 가집니다.
    public void exam2() {
        Map<String, Integer> treeMap = new TreeMap<>();

        // 요소 추가
        treeMap.put("Banana", 200);
        treeMap.put("Apple", 100);
        treeMap.put("Orange", 300);
        treeMap.put("Grape", 150);

        System.out.println("TreeMap elements: " + treeMap);

        // 첫 번째 요소의 키 얻기
        String firstKey = ((TreeMap<String, Integer>) treeMap).firstKey();
        System.out.println("First key: " + firstKey);

        // 마지막 요소의 값 얻기
        int lastValue = ((TreeMap<String, Integer>) treeMap).lastEntry().getValue();
        System.out.println("Last value: " + lastValue);

        // 요소 제거
        treeMap.remove("Apple");
        System.out.println("After removing 'Apple': " + treeMap);

        // 순회 방법 1: entrySet() 사용
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 순회 방법 2: keySet() 사용
        for (String key : treeMap.keySet()) {
            Integer value = treeMap.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }

        // 순회 방법 3: values() 사용
        for (Integer value : treeMap.values()) {
            System.out.println("Value: " + value);
        }
    }

    public static void main(String[] args) {
    }
}
