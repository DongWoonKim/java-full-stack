package com.example.tobi.springbootbasicboard.dto;

import com.example.tobi.springbootbasicboard.model.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;

    public Member toMember() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .build();
    }
}
