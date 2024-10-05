package com.example.tobi.springbootbasicboard.config.security;

import com.example.tobi.springbootbasicboard.dto.SignInResponseDTO;
import com.example.tobi.springbootbasicboard.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal(); // CustomUserDetails 가져오기
        Member member = userDetails.getMember(); // Member 정보 가져오기

        response.setStatus(HttpServletResponse.SC_OK); // 응답 상태 코드 200 (OK)
        response.setContentType("application/json;charset=UTF-8"); // JSON 형식으로 응답

        // 세션 설정
        HttpSession session = request.getSession();
        session.setAttribute("userId", member.getUserId());
        session.setAttribute("userName", member.getUserName());

        SignInResponseDTO build = SignInResponseDTO.builder()
                .isLoggedIn(true)
                .message("로그인 성공")
                .url("/")
                .userId(member.getUserId())
                .userName(member.getUserName())
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(build));
    }
}
