package com.example.spring.springbootbasicboardv2.dto;

import com.example.spring.springbootbasicboardv2.model.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInRequestDTO {
    private String username;
    private String password;

    public Member toMember() {
        return Member.builder()
                .userId(username)
                .password(password)
                .build();
    }
}
