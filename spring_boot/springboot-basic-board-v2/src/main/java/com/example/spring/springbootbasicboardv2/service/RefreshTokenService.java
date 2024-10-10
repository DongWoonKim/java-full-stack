package com.example.spring.springbootbasicboardv2.service;

import com.example.spring.springbootbasicboardv2.mapper.RefreshTokenMapper;
import com.example.spring.springbootbasicboardv2.model.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenMapper refreshTokenMapper;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenMapper.findByRefreshToken(refreshToken);
    }

}
