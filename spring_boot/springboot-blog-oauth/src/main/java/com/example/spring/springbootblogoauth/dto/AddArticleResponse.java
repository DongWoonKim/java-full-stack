package com.example.spring.springbootblogoauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddArticleResponse {

    private Long id;
    private String title;
    private String content;

    @Builder
    public AddArticleResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
