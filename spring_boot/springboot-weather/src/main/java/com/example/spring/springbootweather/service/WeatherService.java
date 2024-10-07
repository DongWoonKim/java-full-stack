package com.example.spring.springbootweather.service;

import com.example.spring.springbootweather.client.WeatherClient;
import com.example.spring.springbootweather.dto.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;
    private final ObjectMapper objectMapper;

    @Value("${weather.api.key}")
    private String serviceKey;  // YML 파일에서 주입

    public String getWeatherData() {
        int numOfRows = 10;
        int pageNo = 1;
        String dataType = "JSON";
        String baseDate = "20241007"; // YYYYMMDD 형식
        String baseTime = "1400";     // 예: HHMM 형식, 기상청 예보 시간에 맞게
        int nx = 60;                  // 좌표값 (X), 서울
        int ny = 127;                 // 좌표값 (Y), 서울

        try {
            String weatherData = weatherClient.getWeatherData(serviceKey, numOfRows, pageNo, dataType, baseDate, baseTime, nx, ny);
            WeatherResponse weatherResponse = objectMapper.readValue(weatherData, WeatherResponse.class);

            // 변환된 데이터를 출력
            System.out.println(weatherResponse.getResponse().getHeader().getResultCode());
            System.out.println(weatherResponse.getResponse().getBody().getItems());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return weatherClient.getWeatherData(serviceKey, numOfRows, pageNo, dataType, baseDate, baseTime, nx, ny);
    }

}
