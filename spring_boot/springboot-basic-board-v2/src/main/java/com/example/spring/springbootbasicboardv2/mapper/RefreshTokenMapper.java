package com.example.spring.springbootbasicboardv2.mapper;

import com.example.spring.springbootbasicboardv2.model.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {
    RefreshToken findByUserId(Long userId);
    RefreshToken findByRefreshToken(String refreshToken);
}
