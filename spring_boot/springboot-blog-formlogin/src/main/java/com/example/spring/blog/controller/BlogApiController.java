package com.example.spring.blog.controller;

import com.example.spring.blog.dto.AddArticleRequest;
import com.example.spring.blog.dto.AddArticleResponse;
import com.example.spring.blog.dto.GetArticleResponse;
import com.example.spring.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<AddArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        AddArticleResponse save = blogService.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(save);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<GetArticleResponse>> getAllArticles() {
        return ResponseEntity
                .ok()
                .body( blogService.findAll() );
    }

}
