package com.example.spring.springbootbasicboardv2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequestDTO {
    private String refreshToken;
}
