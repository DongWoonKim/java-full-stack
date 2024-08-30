package com.example.firstproject.etity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    // JPA는 엔티티 클래스(예: Article)를 관리할 때, 다양한 작업을 수행하기 위해 리플렉션(Reflection)을 사용하여 객체를 생성합니다.
    // 리플렉션을 통해 객체를 생성할 때, JPA는 기본 생성자(no-arg constructor)를 사용합니다.
    // 이 생성자는 매개변수 없이 객체를 생성할 수 있어야 하며, 엔티티를 로딩하거나 쿼리 결과를 가져올 때 JPA가 이를 사용합니다.
    // 기본 생성자가 없으면, JPA는 객체를 생성할 수 없기 때문에 위와 같은 오류가 발생합니다.
    // 해결 방법: 기본 생성자 추가
    // 기본 생성자를 추가하면 이 문제를 해결할 수 있습니다.
    // 일반적으로, 기본 생성자는 public 또는 protected로 선언하며, 엔티티 클래스의 필드 값을 기본 값으로 초기화하는 역할을 합니다.

}
