package com.example.spring.springbootbasicboardv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied"; // accessDenied.html 템플릿 반환
    }
}
