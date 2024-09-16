package com.example.spring.blog.domain;

import com.example.spring.blog.dto.AddArticleResponse;
import com.example.spring.blog.dto.GetArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public AddArticleResponse toAddArticleResponse() {
        return AddArticleResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }

    public GetArticleResponse toGetArticleResponse() {
        return GetArticleResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }

}
