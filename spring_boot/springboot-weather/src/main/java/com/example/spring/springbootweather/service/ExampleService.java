package com.example.spring.springbootweather.service;

import com.example.spring.springbootweather.client.ExampleClient;
import com.example.spring.springbootweather.dto.DataRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleClient exampleClient;

    // GET 요청 호출
    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }

    // POST 요청 호출
    public String createData(String name, int value) {
        return exampleClient.createData(
                DataRequest.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    // PUT 요청 호출
    public String updateData(Long id, String name, int value) {
        return exampleClient.updateData(
                id,
                DataRequest.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    // DELETE 요청 호출
    public String deleteData(Long id) {
        return exampleClient.deleteData(id);
    }
}
