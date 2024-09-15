package com.example.basic.controller;

import com.example.basic.domain.Article;
import com.example.basic.domain.User;
import com.example.basic.dto.AddArticleRequest;
import com.example.basic.dto.UpdateArticleRequest;
import com.example.basic.repository.BlogRepository;
import com.example.basic.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest // 테스트용 애플리케이션 컨텍스트
//@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {

//    @Autowired
    private MockMvc mockMvc;

//    @Autowired
    private ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

//    @Autowired
    private WebApplicationContext context;

//    @Autowired
    private BlogRepository blogRepository;

//    @Autowired
    private UserRepository userRepository;

    User user;

//    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        blogRepository.deleteAll();
    }

//    @BeforeEach
    public void setSecurityContext() {
        userRepository.deleteAll();
        user = userRepository.save(
                User.builder()
                        .email("test@example.com")
                        .password("test")
                        .role("ROLE_USER")
                        .build()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
            )
        );
    }

//    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest addArticleRequest = AddArticleRequest.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .build();
        final String requestBody = objectMapper.writeValueAsString(addArticleRequest);

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("username");

        // when
        ResultActions result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .principal(principal)
                        .content(requestBody)
        );

        // then
        result.andExpect(
                status().isCreated()
        );

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

//    @Test
    public void findAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build()
        );

        // when
        final ResultActions resultActions = mockMvc.perform(
                get(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }

//    @Test
    public void findArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saved = blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build()
        );

        // when
        final ResultActions resultActions = mockMvc.perform(
                get(url, saved.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

//    @Test
    public void deleteArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saved = blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build()
        );

        // when
        mockMvc.perform(
                delete(url, saved.getId())
        ).andExpect(status().isOk());

        // then
        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(0);
    }

//    @Test
    public void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saved = blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build()
        );

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);

        // when
        ResultActions result = mockMvc.perform(
                put(url, saved.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        );

        // then
        result.andExpect(status().isOk());

        Article article = blogRepository.findById(saved.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }

}