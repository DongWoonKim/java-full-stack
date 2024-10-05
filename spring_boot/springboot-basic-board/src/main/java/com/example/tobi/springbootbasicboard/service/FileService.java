package com.example.tobi.springbootbasicboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    // 파일이 저장될 경로
    private final String UPLOADED_FOLDER = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "하이미디어" + File.separator + "upload" + File.separator;

    public String fileUpload(MultipartFile file) {

        // 파일 저장 로직
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            return UPLOADED_FOLDER + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
