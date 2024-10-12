package com.example.spring.springbootbasicboardv2.dto;

import com.example.spring.springbootbasicboardv2.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponseDTO {
    private long id;
    private String userId;
    private String userName;
    private Role role;
}
