package com.example.spring.blog.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateArticleResponse {

    private String title;
    private String content;

    @Builder
    public UpdateArticleResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
