package com.example.spring.blog.controller;

import com.example.spring.blog.domain.Article;
import com.example.spring.blog.dto.AddArticleRequest;
import com.example.spring.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void MockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        blogRepository.deleteAll();
    }

    @Test
    void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "test title";
        final String content = "test content";
        final AddArticleRequest addArticleRequest = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(addArticleRequest);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody)
        );

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        List<Article> articles = blogRepository.findAll();

        AssertionsForClassTypes.assertThat(articles.size()).isEqualTo(1);
        AssertionsForClassTypes.assertThat(articles.get(0).getTitle()).isEqualTo(title);
        AssertionsForClassTypes.assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @Test
    void getAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "test title";
        final String content = "test content";

        blogRepository.save(new Article(title, content));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(content));

    }

    @Test
    void getArticleById() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "test title";
        final String content = "test content";

        Article saved = blogRepository.save(new Article(title, content));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url, saved.getId())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        Article article = blogRepository.findById(saved.getId()).orElseThrow(() -> new IllegalArgumentException("Not found"));
        AssertionsForClassTypes.assertThat(article.getId()).isEqualTo(saved.getId());
        AssertionsForClassTypes.assertThat(article.getTitle()).isEqualTo(title);
        AssertionsForClassTypes.assertThat(article.getContent()).isEqualTo(content);
    }

    @Test
    void deleteArticleById() throws Exception {

        // given
        final String url = "/api/articles/{id}";
        final String title = "test title";
        final String content = "test content";

        Article saved = blogRepository.save(new Article(title, content));

        // when
        mockMvc.perform(
                MockMvcRequestBuilders.delete(url, saved.getId())
        ).andExpect( MockMvcResultMatchers.status().isOk() );

        // then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();

    }

}