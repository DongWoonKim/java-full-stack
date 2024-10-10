package com.example.spring.springbootbasicboardv2.dto;

import com.example.spring.springbootbasicboardv2.enums.Role;
import com.example.spring.springbootbasicboardv2.model.Member;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;
    private Role role;

    public Member toMember(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return Member.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .userName(userName)
                .role(role)
                .build();
    }
}
