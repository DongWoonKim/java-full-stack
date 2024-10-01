package com.example.spring.springbootv1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {

    // 쿠키 설정 페이지
    @GetMapping("/set-cookie-page")
    public String setCookiePage() {
        return "set-cookie";
    }

    // 쿠키 설정 처리
    @PostMapping("/set-cookie")
    public String setCookie(@RequestParam String username, HttpServletResponse response, Model model) {
        // 쿠키 생성
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 1주일
        cookie.setHttpOnly(true); // HttpOnly 설정
        cookie.setPath("/");

        // 쿠키를 응답에 추가
        response.addCookie(cookie);

        model.addAttribute("message", "쿠키가 설정되었습니다.");
        return "cookie-result";
    }

    // 쿠키 읽기
    @GetMapping("/get-cookie")
    public String getCookie(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        if (username != null) {
            model.addAttribute("username", username);
            model.addAttribute("message", "쿠키에서 사용자 이름을 읽었습니다.");
        } else {
            model.addAttribute("message", "쿠키가 존재하지 않습니다.");
        }

        return "cookie-result";
    }

    // 쿠키 삭제
    @GetMapping("/delete-cookie")
    public String deleteCookie(HttpServletResponse response, Model model) {
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // 유효 기간을 0으로 설정하여 쿠키 삭제
        cookie.setPath("/");

        response.addCookie(cookie);
        model.addAttribute("message", "쿠키가 삭제되었습니다.");

        return "cookie-result";
    }

}
