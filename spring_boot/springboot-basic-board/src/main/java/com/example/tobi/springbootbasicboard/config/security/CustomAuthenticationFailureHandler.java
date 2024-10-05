package com.example.tobi.springbootbasicboard.config.security;

import com.example.tobi.springbootbasicboard.dto.SignInResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized 상태 설정
        response.setContentType("application/json;charset=UTF-8"); // JSON 응답으로 설정

        SignInResponseDTO build = SignInResponseDTO.builder()
                .isLoggedIn(false)
                .message("로그인 실패")
                .url("/")
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(build)); // JSON 응답 전송
    }
}
