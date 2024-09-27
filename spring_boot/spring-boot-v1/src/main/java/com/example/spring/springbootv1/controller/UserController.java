package com.example.spring.springbootv1.controller;

import com.example.spring.springbootv1.model.User;
import com.example.spring.springbootv1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/register")
    public String signUp(Model model) {
        return "sign-up";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
