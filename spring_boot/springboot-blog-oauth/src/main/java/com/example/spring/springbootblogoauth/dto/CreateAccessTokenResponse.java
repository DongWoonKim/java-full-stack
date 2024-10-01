package com.example.spring.springbootblogoauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAccessTokenResponse {
    private String accessToken;
}
