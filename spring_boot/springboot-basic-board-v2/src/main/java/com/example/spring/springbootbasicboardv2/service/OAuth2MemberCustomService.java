package com.example.spring.springbootbasicboardv2.service;

import com.example.spring.springbootbasicboardv2.mapper.MemberMapper;
import com.example.spring.springbootbasicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2MemberCustomService extends DefaultOAuth2UserService {

    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        saveOrUpdate(oAuth2User);
        return oAuth2User;
    }

    private void saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");

        Member byUserName = memberMapper.findByUserName(name);
        if (byUserName != null) {

        } else {
            memberMapper.saved(
                    Member.builder()
                            .userId(email)
                            .userName(name)
                            .build()
            );
        }

    }
}
