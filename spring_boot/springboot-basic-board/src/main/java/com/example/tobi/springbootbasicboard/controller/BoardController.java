package com.example.tobi.springbootbasicboard.controller;

import com.example.tobi.springbootbasicboard.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @GetMapping("/")
    public String boardList(HttpSession session, Model model) {
        SessionUtil.setSession(session, model);
        return "board-list";
    }

    @GetMapping("/detail")
    public String detail(
            @RequestParam("id") Long id,
            HttpSession session,
            Model model
    ) {
        SessionUtil.setSession(session, model);
        model.addAttribute("id", id);
        return "board-detail";
    }

    @GetMapping("/write")
    public String write(HttpSession session, Model model) {
        SessionUtil.setSession(session, model);
        return "board-write";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpSession session, Model model) {
        SessionUtil.setSession(session, model);
        model.addAttribute("id", id);
        return "board-update";
    }



}
