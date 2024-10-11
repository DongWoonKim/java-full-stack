package com.example.spring.springbootbasicboardv2.controller;

import com.example.spring.springbootbasicboardv2.config.jwt.TokenProvider;
import com.example.spring.springbootbasicboardv2.dto.SignInResponseDTO;
import com.example.spring.springbootbasicboardv2.model.Member;
import com.example.spring.springbootbasicboardv2.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenProvider tokenProvider;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키에서 Refresh Token을 추출
        String refreshToken = getRefreshTokenFromCookies(request);

        if (refreshToken != null && tokenProvider.validToken(refreshToken) == 1) {
            // Refresh Token이 유효하면 새로운 Access Token을 발급
            Member member = tokenProvider.getTokenDetails(refreshToken);
            String newAccessToken = tokenProvider.generateToken(member, Duration.ofHours(2));

            // Refresh Token 생성 (긴 유효기간)
            String newRefreshToken = tokenProvider.generateToken(member, Duration.ofDays(3));

            // Refresh Token을 HttpOnly 쿠키에 저장
            CookieUtil.addCookie(response, "refreshToken", newRefreshToken, 7 * 24 * 60 * 60);

            // 새로 발급된 Access Token을 응답 헤더에 포함시킴
            response.setHeader("Authorization", "Bearer " + newAccessToken);

            return ResponseEntity.ok( SignInResponseDTO.builder().token(newAccessToken).build() );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh Token이 유효하지 않습니다.");
        }
    }

    public String getRefreshTokenFromCookies(HttpServletRequest request) {
        // 요청에 쿠키가 있는지 확인
        if (request.getCookies() != null) {
            // 쿠키 배열에서 'refreshToken'이라는 이름의 쿠키를 찾아 반환
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    return cookie.getValue(); // Refresh Token 값 반환
                }
            }
        }
        // 'refreshToken' 쿠키가 없으면 null 반환
        return null;
    }

}
