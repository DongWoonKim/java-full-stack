package com.example.spring.springbootweather.controller;

import com.example.spring.springbootweather.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class ExampleController {
    private final ExampleService exampleService;

    // GET 요청 처리
    @GetMapping("/data/{id}")
    public String getData(@PathVariable("id") Long id) {
        return exampleService.getDataById(id);
    }

    // POST 요청 처리
    @PostMapping("/data")
    public String createData(@RequestParam String name, @RequestParam int value) {
        return exampleService.createData(name, value);
    }

    // PUT 요청 처리
    @PutMapping("/data/{id}")
    public String updateData(@PathVariable("id") Long id, @RequestParam String name, @RequestParam int value) {
        return exampleService.updateData(id, name, value);
    }

    // DELETE 요청 처리
    @DeleteMapping("/data/{id}")
    public String deleteData(@PathVariable("id") Long id) {
        return exampleService.deleteData(id);
    }
}
