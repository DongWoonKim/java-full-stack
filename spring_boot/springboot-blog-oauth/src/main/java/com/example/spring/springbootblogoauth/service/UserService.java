package com.example.spring.springbootblogoauth.service;

import com.example.spring.blog.dto.AddUserRequest;
import com.example.spring.blog.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest addUserRequest) {
        return usersRepository.save( addUserRequest.toUsers(bCryptPasswordEncoder) )
                .getId();
    }

}
