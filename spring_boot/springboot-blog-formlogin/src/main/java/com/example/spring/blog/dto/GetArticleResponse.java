package com.example.spring.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GetArticleResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

}
