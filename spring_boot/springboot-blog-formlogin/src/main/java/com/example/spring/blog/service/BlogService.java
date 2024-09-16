package com.example.spring.blog.service;

import com.example.spring.blog.dto.AddArticleRequest;
import com.example.spring.blog.dto.AddArticleResponse;
import com.example.spring.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public AddArticleResponse save(AddArticleRequest request) {
        return blogRepository.save( request.toEntity() )
                .toAddArticleResponse();
    }

}
