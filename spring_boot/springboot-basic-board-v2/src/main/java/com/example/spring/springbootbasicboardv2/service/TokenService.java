package com.example.spring.springbootbasicboardv2.service;

import com.example.spring.springbootbasicboardv2.config.jwt.TokenProvider;
import com.example.spring.springbootbasicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        Member byUserId = memberService.findByUserId(userId);

        return tokenProvider.generateToken(byUserId, Duration.ofHours(2));

    }

}
