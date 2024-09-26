package com.example.spring.springbootblogoauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateArticleRequest {

    private String title;
    private String content;

    @Builder
    public UpdateArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
