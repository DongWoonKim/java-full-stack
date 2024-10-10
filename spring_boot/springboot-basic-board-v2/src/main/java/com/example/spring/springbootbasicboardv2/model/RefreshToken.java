package com.example.spring.springbootbasicboardv2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken {
    private Long id;
    private Long userId;
    private String refreshToken;
}
