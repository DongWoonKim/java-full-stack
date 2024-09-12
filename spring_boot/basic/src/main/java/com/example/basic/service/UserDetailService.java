package com.example.basic.service;

import com.example.basic.config.CustomUserDetails;
import com.example.basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getNickname(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}
