package com.example.spring.springbootbasicboardv2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAccessTokenResponseDTO {
    private String accessToken;
}
