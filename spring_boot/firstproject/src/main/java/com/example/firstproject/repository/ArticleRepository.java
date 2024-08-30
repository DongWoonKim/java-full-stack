package com.example.firstproject.repository;

import com.example.firstproject.etity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
