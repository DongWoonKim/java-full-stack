package com.example.basic.service;

import com.example.basic.domain.Article;
import com.example.basic.dto.AddArticleRequest;
import com.example.basic.dto.UpdateArticleRequest;
import com.example.basic.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request, String username) {
        return blogRepository.save( request.toArticle(username) );
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Not found : " + id)
                );
    }

    public void delete(Long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));

        authorizeArticle(article);
        blogRepository.delete(article);
//        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id,  UpdateArticleRequest request) {
//        Article article = findById(id);
//        article.update(request.getTitle(), request.getContent());

        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));

        authorizeArticle(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글 작성한 유저인지 확인
    private static void authorizeArticle(Article article) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!article.getAuthor().equals(name)) {
            throw new IllegalArgumentException("Not authorized");
        }
    }
}
