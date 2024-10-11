package com.example.spring.springbootbasicboardv2.controller;

import com.example.spring.springbootbasicboardv2.dto.CreateAccessTokenRequestDTO;
import com.example.spring.springbootbasicboardv2.dto.CreateAccessTokenResponseDTO;
import com.example.spring.springbootbasicboardv2.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponseDTO> createNewAccessToken(@RequestBody CreateAccessTokenRequestDTO request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    CreateAccessTokenResponseDTO.builder()
                            .accessToken(newAccessToken)
                            .build()
                );
    }
}
