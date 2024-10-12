package com.example.spring.springbootbasicboardv2.controller;

import com.example.spring.springbootbasicboardv2.config.jwt.TokenProvider;
import com.example.spring.springbootbasicboardv2.config.security.CustomUserDetails;
import com.example.spring.springbootbasicboardv2.dto.*;
import com.example.spring.springbootbasicboardv2.model.Member;
import com.example.spring.springbootbasicboardv2.service.MemberService;
import com.example.spring.springbootbasicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    @PostMapping("/join")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        memberService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return ResponseEntity.ok(
                SignUpResponseDTO.builder()
                        .url("/member/login")
                        .build()
        );
    }

    @GetMapping("/user/info")
    public ResponseEntity<UserInfoResponseDTO> getUserInfo(HttpServletRequest request) {
        Member member = (Member) request.getAttribute("member");
        return ResponseEntity.ok(
                UserInfoResponseDTO.builder()
                        .id(member.getId())
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .role(member.getRole())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDTO> signIn(
            @RequestBody SignInRequestDTO requestDTO,
            HttpServletResponse response
    ) {

        // 사용자 인증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getUsername(),
                        requestDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Member member = ((CustomUserDetails) authentication.getPrincipal()).getMember();
        System.out.println("member 33 :: " + member);

        // Access Token 생성 (짧은 유효기간)
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));

        // Refresh Token 생성 (긴 유효기간)
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(3));

        // Refresh Token을 HttpOnly 쿠키에 저장
        CookieUtil.addCookie(response, "refreshToken", refreshToken, 7 * 24 * 60 * 60);

        // 토큰 반환
        return ResponseEntity.ok(
                SignInResponseDTO.builder()
                        .isLoggedIn(true)
                        .message("로그인 성공")
                        .url("/")
                        .token(accessToken)
                        .build()
        );
    }

}
