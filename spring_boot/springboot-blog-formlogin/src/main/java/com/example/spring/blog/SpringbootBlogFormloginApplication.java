package com.example.spring.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootBlogFormloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogFormloginApplication.class, args);
    }

}
