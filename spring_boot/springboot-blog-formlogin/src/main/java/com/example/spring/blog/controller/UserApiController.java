package com.example.spring.blog.controller;

import com.example.spring.blog.dto.AddUserRequest;
import com.example.spring.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signUp(AddUserRequest request) {
        userService.save(request);

        return "redirect:/login";
    }

}
