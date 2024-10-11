package com.example.spring.springbootbasicboardv2.service;

import com.example.spring.springbootbasicboardv2.config.security.CustomUserDetails;
import com.example.spring.springbootbasicboardv2.mapper.MemberMapper;
import com.example.spring.springbootbasicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findByUserId(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return CustomUserDetails.builder()
                .member(member)
                .roles(List.of(String.valueOf(member.getRole())))
                .build(); // Member 정보를 가진 CustomUserDetails 반환
    }
}