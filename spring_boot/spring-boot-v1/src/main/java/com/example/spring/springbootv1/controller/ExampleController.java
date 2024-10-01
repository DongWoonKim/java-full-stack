package com.example.spring.springbootv1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/api/data")
    public String data() {
        return "Some data";
    }
}
