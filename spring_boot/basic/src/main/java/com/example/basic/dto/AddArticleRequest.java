package com.example.basic.dto;

import com.example.basic.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toArticle() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
