package com.example.spring.blog.service;

import com.example.spring.blog.domain.Article;
import com.example.spring.blog.dto.AddArticleRequest;
import com.example.spring.blog.dto.AddArticleResponse;
import com.example.spring.blog.dto.GetArticleResponse;
import com.example.spring.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public AddArticleResponse save(AddArticleRequest request) {
        return blogRepository.save( request.toEntity() )
                .toAddArticleResponse();
    }

    // 글 목록 조회
    public List<GetArticleResponse> findAll() {
        return blogRepository.findAll().stream()
                .map(Article::toGetArticleResponse)
                .collect(Collectors.toList());
    }

    // 글 조회
    public GetArticleResponse findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id))
                .toGetArticleResponse();
    }

}
