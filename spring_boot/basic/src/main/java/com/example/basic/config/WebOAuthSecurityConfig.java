package com.example.basic.config;

import com.example.basic.config.jwt.TokenProvider;
import com.example.basic.config.oauth.OAuth2SuccessHandler;
import com.example.basic.config.oauth.OAuth2UserCustomService;
import com.example.basic.config.oauth.Oauth2AuthorizationrequestBasedOnCookieRepository;
import com.example.basic.repository.RefreshTokenRepository;
import com.example.basic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class WebOAuthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/img/**"),
                        new AntPathRequestMatcher("/css/**"),
                        new AntPathRequestMatcher("/js/**")
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(new AntPathRequestMatcher("/api/token")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated().anyRequest().permitAll()
                )
                .oauth2Login(
                        oauth2 -> oauth2.loginPage("/login")
                                .authorizationEndpoint(
                                        authorizationEndpoint -> authorizationEndpoint
                                                .authorizationRequestRepository( oauth2AuthorizationrequestBasedOnCookieRepository() )
                                ).userInfoEndpoint(
                                        userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserCustomService)
                                ).successHandler( oAuth2SuccessHandler() )
                )
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .defaultAuthenticationEntryPointFor(
                                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                        new AntPathRequestMatcher("/api/**")
                                )
                )
                .build();

    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(
                tokenProvider,
                refreshTokenRepository,
                oauth2AuthorizationrequestBasedOnCookieRepository(),
                userService
        );
    }

    @Bean
    public Oauth2AuthorizationrequestBasedOnCookieRepository oauth2AuthorizationrequestBasedOnCookieRepository() {
        return new Oauth2AuthorizationrequestBasedOnCookieRepository();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        return new TokenAuthenticationFilter(tokenProvider);
    }


}
