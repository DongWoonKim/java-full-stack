package com.example.spring.springbootblogoauth.config.jwt;

import com.example.spring.springbootblogoauth.domain.Users;
import com.example.spring.springbootblogoauth.repository.UsersRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @BeforeEach
    public void setUp() {
        usersRepository.deleteAll();
    }

    // generateToken() 검증 테스트
    @DisplayName("generateToken() : 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void generateToken() {
        // given
        Users testUser = usersRepository.save(
                Users.builder()
                        .email("user@gamil.com")
                        .password("test")
                        .build()
        );

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(2));

        // then
        Long id = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())  // SecretKey 객체 사용
                .build()                    // 파서 빌드
                .parseClaimsJws(token)      // 토큰 파싱
                .getBody()                  // 클레임 추출
                .get("id", Long.class);

        assertThat(id).isEqualTo(testUser.getId());
    }

    @DisplayName("validToken() : 만료된 토큰인 유효성 검증에 실패한다.")
    @Test
    void validToken_invalidToken() {
        // given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isTrue();

    }

    @DisplayName("getAuthentication() : 토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        // given
        String userEmail = "user@gamil.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat( ((UserDetails)authentication.getPrincipal()).getUsername().equals(userEmail) );
    }

    @DisplayName("getUserId() : 토큰으로 유저 ID를 가져올 수 있다.")
    @Test
    void getUserId() {
        // given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        Long tokenProviderUserId = tokenProvider.getUserId(token);

        // then
        assertThat(tokenProviderUserId).isEqualTo(userId);

    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
