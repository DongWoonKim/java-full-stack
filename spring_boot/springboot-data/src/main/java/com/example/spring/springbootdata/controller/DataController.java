package com.example.spring.springbootdata.controller;

import com.example.spring.springbootdata.dto.DataRequest;
import com.example.spring.springbootdata.dto.DataResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private Map<Long, DataResponse> dataStore = new HashMap<>();
    private Long idCounter = 1L;

    // 초기 데이터를 추가하는 메서드
    @PostConstruct
    public void initDataStore() {
        dataStore.put(idCounter++, new DataResponse(1L, "Item 1", 100));
        dataStore.put(idCounter++, new DataResponse(2L, "Item 2", 200));
        dataStore.put(idCounter++, new DataResponse(3L, "Item 3", 300));
        dataStore.put(idCounter++, new DataResponse(4L, "Item 4", 400));
        dataStore.put(idCounter++, new DataResponse(5L, "Item 5", 500));
    }

    // GET 요청: ID로 데이터 조회
    @GetMapping("/{id}")
    public DataResponse getData(@PathVariable("id") Long id) {
        DataResponse data = dataStore.get(id);
        if (data == null) {
            throw new RuntimeException("Data not found with ID: " + id);
        }
        return data;
    }

    // POST 요청: 데이터 생성
    @PostMapping
    public DataResponse createData(@RequestBody DataRequest dataRequest) {
        DataResponse newData = DataResponse.builder()
                .id(idCounter)
                .name(dataRequest.getName())
                .value(dataRequest.getValue())
                .build();

        dataStore.put(idCounter, newData);
        idCounter++;

        return newData;
    }

    // PUT 요청: ID로 데이터 수정
    @PutMapping("/{id}")
    public DataResponse updateData(@PathVariable("id") Long id, @RequestBody DataRequest dataRequest) {
        DataResponse existingData = dataStore.get(id);
        if (existingData == null) {
            throw new RuntimeException("Data not found with ID: " + id);
        }
        existingData.setName(dataRequest.getName());
        existingData.setValue(dataRequest.getValue());
        dataStore.put(id, existingData);
        return existingData;
    }

    // DELETE 요청: ID로 데이터 삭제
    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable("id") Long id) {
        DataResponse removedData = dataStore.remove(id);
        if (removedData == null) {
            throw new RuntimeException("Data not found with ID: " + id);
        }
        return "Data with ID " + id + " deleted.";
    }

}
