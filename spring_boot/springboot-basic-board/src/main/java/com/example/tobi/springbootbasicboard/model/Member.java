package com.example.tobi.springbootbasicboard.model;

import com.example.tobi.springbootbasicboard.config.security.CustomUserDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Member {
    private long id;
    private String userId;
    private String password;
    private String userName;
}
