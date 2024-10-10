package com.example.spring.springbootbasicboard.mapper;

import com.example.spring.springbootbasicboard.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void signUp(Member member);
    Member signIn(String userId);
}
