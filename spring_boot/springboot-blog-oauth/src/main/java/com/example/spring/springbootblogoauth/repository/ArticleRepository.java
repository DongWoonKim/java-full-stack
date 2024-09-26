package com.example.spring.springbootblogoauth.repository;

import com.example.spring.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
