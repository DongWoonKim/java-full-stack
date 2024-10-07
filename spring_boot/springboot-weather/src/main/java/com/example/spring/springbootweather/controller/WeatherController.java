package com.example.spring.springbootweather.controller;

import com.example.spring.springbootweather.dto.weather.WeatherResponse;
import com.example.spring.springbootweather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    // https://www.data.go.kr/
    // 기상청_단기예보
    @GetMapping("/weather")
    public WeatherResponse getWeatherData() {
        return weatherService.getWeatherData();
    }

}
