package com.example.spring.springbootbasicboardv2.controller;

import com.example.spring.springbootbasicboardv2.config.jwt.TokenProvider;
import com.example.spring.springbootbasicboardv2.config.security.CustomUserDetails;
import com.example.spring.springbootbasicboardv2.dto.SignInRequestDTO;
import com.example.spring.springbootbasicboardv2.dto.SignInResponseDTO;
import com.example.spring.springbootbasicboardv2.dto.SignUpRequestDTO;
import com.example.spring.springbootbasicboardv2.dto.SignUpResponseDTO;
import com.example.spring.springbootbasicboardv2.model.Member;
import com.example.spring.springbootbasicboardv2.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        // JWT 토큰 생성
        String token = tokenProvider.generateToken(member, Duration.ofHours(2));

        // JWT 토큰을 HttpOnly 쿠키에 저장
        Cookie jwtCookie = new Cookie("token", token);
        jwtCookie.setHttpOnly(true); // JavaScript로 접근 불가
        jwtCookie.setSecure(true);   // HTTPS에서만 사용 (배포 환경에서 사용)
        jwtCookie.setPath("/");      // 쿠키의 경로 설정 (모든 경로에서 사용)
        jwtCookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키 만료 시간 설정 (1주일)
        response.addCookie(jwtCookie);


        // 토큰 반환
        return ResponseEntity.ok(
                SignInResponseDTO.builder()
                        .isLoggedIn(true)
                        .message("로그인 성공")
                        .url("/")
                        .token(token)
                        .build()
        );
    }

}
