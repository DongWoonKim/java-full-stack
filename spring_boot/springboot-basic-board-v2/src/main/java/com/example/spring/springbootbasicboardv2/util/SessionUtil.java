package com.example.spring.springbootbasicboardv2.util;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class SessionUtil {

    public static void setSession(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userName", userName);
        model.addAttribute("userId", userId);
    }

}
