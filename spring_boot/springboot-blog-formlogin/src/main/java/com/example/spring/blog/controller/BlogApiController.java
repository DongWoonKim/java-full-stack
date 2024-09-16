package com.example.spring.blog.controller;

import com.example.spring.blog.dto.*;
import com.example.spring.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
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

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<GetArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body( blogService.findById(id) );
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<UpdateArticleResponse> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        return ResponseEntity
                .ok()
                .body(blogService.updateArticleById(id, request));
    }
}
