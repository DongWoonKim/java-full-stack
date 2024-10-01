package com.example.spring.springbootv1.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 (필요 시)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 요청 정보 로깅
        System.out.println("Request URI: " + httpRequest.getRequestURI());
        System.out.println("Request Method: " + httpRequest.getMethod());

        // 필터 체인 계속해서 다음 필터 또는 서블릿으로 전달
        chain.doFilter(request, response);

        // 응답 상태 코드 로깅
        System.out.println("Response Status: " + httpResponse.getStatus());
    }

    @Override
    public void destroy() {
        // 필터 종료 시 처리 (필요 시)
    }
}
