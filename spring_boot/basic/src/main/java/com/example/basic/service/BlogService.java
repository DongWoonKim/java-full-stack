package com.example.basic.service;

import com.example.basic.domain.Article;
import com.example.basic.dto.AddArticleRequest;
import com.example.basic.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save( request.toArticle() );
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

}
