package com.example.basic.config.jwt;

import com.example.basic.domain.User;
import com.example.basic.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class TokenProviderTest {

//    @Autowired
    private TokenProvider tokenProvider;
//    @Autowired
    private UserRepository userRepository;
//    @Autowired
    private JwtProperties jwtProperties;

//    @Test
    void generateToken() {
        // given
        userRepository.deleteAll();
        User testUser = userRepository.save(
                User.builder()
                        .email("gauri7891@gmail.com")
                        .password("123456")
                        .build()
        );

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        String secretKey = jwtProperties.getSecretKey(); // Base64로 인코딩된 값
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
        Long userId = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

//    @Test
    void validToken_invaildToken() {
        // given
        String token = JwtFactory.builder()
                .expiresAt(
                        new Date(new Date().getTime() - Duration.ofDays(7).toMillis())
                )
                .build()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isFalse();
    }

//    @Test
    void validToken_vaildToken() {
        // given
        String token = JwtFactory.builder()
                .expiresAt(
                        new Date(new Date().getTime() - Duration.ofDays(7).toMillis())
                )
                .build()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isTrue();
    }

//    @Test
    void getAuthentication() {
        // given
        String userEmail = "gauri7891@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat( ((UserDetails)authentication.getPrincipal()).getUsername() ).isEqualTo(userEmail);
    }

//    @Test
    void getUserId() {
        // given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        Long userIdByToken = tokenProvider.getUserId(token);

        // then
        assertThat(userIdByToken).isEqualTo(userId);
    }

}