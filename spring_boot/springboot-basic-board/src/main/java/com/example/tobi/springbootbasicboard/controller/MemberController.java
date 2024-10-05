package com.example.tobi.springbootbasicboard.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/login")
    public String signIn(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            return "redirect:/";
        }

        return "sign-in";
    }

}
