package com.example.basic.config;

import com.example.basic.config.jwt.TokenProvider;
import com.example.basic.domain.User;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.time.Duration;

//@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;

//    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                new AntPathRequestMatcher("/login"),
//                                new AntPathRequestMatcher("/loginToken"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
//                                .loginPage("/loginToken")
                                .defaultSuccessUrl("/articles")
//                                .successHandler(authenticationSuccessHandler()) // 로그인 성공 핸들러
                )
                .logout(
                        logout -> logout
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();

    }

    // 로그인 성공 시 Access Token 및 Refresh Token 발급
//    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // 인증된 사용자 정보에서 CustomUserDetails 가져오기
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // CustomUserDetails에서 ID 가져오기
            Long userId = userDetails.getId();
            String email = userDetails.getUsername();

            User build = User.builder()
                    .id(userId)
                    .email(email)
                    .build();

            String accessToken = tokenProvider.generateToken( build, Duration.ofHours(2) );
            String refreshToken = tokenProvider.generateToken( build, Duration.ofDays(14) );

            System.out.println(accessToken + " : " + refreshToken);

            // Access Token을 JSON으로 응답
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Refresh Token을 HTTPOnly 쿠키에 저장
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
//            refreshTokenCookie.setSecure(true); // HTTPS 환경에서만 동작하게 설정
            refreshTokenCookie.setSecure(false);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(14 * 24 * 60 * 60); // 14일간 유효

            response.addCookie(refreshTokenCookie);

            // Access Token을 JSON으로 응답
            response.getWriter().write("{\"accessToken\":\"" + accessToken + "\", \"userId\":\"" + userId + "\"}");
        };
    }

//    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);

        return new ProviderManager(authProvider);
    }

//    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
