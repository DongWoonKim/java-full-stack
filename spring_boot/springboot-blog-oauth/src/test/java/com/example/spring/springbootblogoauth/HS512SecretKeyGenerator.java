package com.example.spring.springbootblogoauth;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;

public class HS512SecretKeyGenerator {

    @Test
    void HS512SecretKeyGenerator() {
        // 512비트 (64바이트) 크기의 바이트 배열 생성
        byte[] keyBytes = new byte[64];

        // SecureRandom 인스턴스 생성
        SecureRandom secureRandom = new SecureRandom();

        // 바이트 배열에 랜덤한 값 채우기
        secureRandom.nextBytes(keyBytes);

        // Base64 인코딩을 통해 문자열로 변환
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);

        // 생성된 비밀키 출력
        System.out.println("Generated HS512 Secret Key:");
        System.out.println(secretKey);
    }

}
