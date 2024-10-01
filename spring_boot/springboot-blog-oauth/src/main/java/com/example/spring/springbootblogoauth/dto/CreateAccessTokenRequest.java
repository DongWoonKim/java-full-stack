package com.example.spring.springbootblogoauth.dto;

import lombok.Getter;

@Getter
public class CreateAccessTokenRequest {
    private String refreshToken;
}
