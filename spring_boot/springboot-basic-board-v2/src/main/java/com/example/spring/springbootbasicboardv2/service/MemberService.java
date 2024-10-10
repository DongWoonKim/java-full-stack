package com.example.spring.springbootbasicboardv2.service;

import com.example.spring.springbootbasicboardv2.mapper.MemberMapper;
import com.example.spring.springbootbasicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void signUp(Member member) {
        memberMapper.signUp(member);
    }

    public Member findByUserId(String userId) {
        return memberMapper.findByUserId(userId);
    }

    public Member findByUserId(Long userId) {
        return memberMapper.findById(userId);
    }

}
