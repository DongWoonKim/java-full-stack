package com.example.spring.blog.service;

import com.example.spring.blog.domain.Article;
import com.example.spring.blog.dto.*;
import com.example.spring.blog.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final ArticleRepository articleRepository;

    // 블로그 글 추가 메서드
    public AddArticleResponse save(AddArticleRequest request) {
        return articleRepository.save( request.toEntity() )
                .toAddArticleResponse();
    }

    // 글 목록 조회
    public List<GetArticleResponse> findAll() {
        return articleRepository.findAll().stream()
                .map(Article::toGetArticleResponse)
                .collect(Collectors.toList());
    }

    // 글 조회
    public GetArticleResponse findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id))
                .toGetArticleResponse();
    }

    // 글 삭제
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    // 글 수정
    @Transactional
    public UpdateArticleResponse updateArticleById(Long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));

        article.update( request.getTitle(), request.getContent() );

        return article.toUpdateArticleResponse();
    }

}
