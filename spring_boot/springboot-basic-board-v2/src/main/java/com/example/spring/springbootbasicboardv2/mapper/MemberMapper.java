package com.example.spring.springbootbasicboardv2.mapper;

import com.example.spring.springbootbasicboardv2.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void signUp(Member member);
    Member findByUserId(String userId);
    Member findById(Long userId);
}
