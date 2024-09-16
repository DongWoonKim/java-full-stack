package com.example.spring.blog.controller;

import com.example.spring.blog.dto.GetArticleResponse;
import com.example.spring.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String articles(Model model) {
        List<GetArticleResponse> articles = blogService.findAll();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String article(@PathVariable Long id, Model model) {
        GetArticleResponse article = blogService.findById(id);
        model.addAttribute("article", article);

        return "article";
    }

}
