package com.example.spring.springbootv1.config;

import com.example.spring.springbootv1.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/api/*"); // 필터가 적용될 URL 패턴
        registrationBean.setOrder(1); // 필터 순서 설정

        return registrationBean;
    }
}
