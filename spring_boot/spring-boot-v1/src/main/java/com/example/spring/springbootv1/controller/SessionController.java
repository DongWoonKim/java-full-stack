package com.example.spring.springbootv1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {
    // 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            HttpSession session) {

        // 세션에 사용자 이름 저장
        session.setAttribute("username", username);

        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {
        // 세션에서 사용자 이름 가져오기
        String username = (String) session.getAttribute("username");

        // 세션에 값이 있으면 모델에 추가
        if (username != null) {
            model.addAttribute("username", username);
        }

        return "login"; // login.html 템플릿으로 이동
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
