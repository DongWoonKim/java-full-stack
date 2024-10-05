package com.example.tobi.springbootbasicboard.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

    public Resource downloadFile(String fileName) {
        try {
            // 파일 경로 설정
            Path filePath = Paths.get(UPLOADED_FOLDER + fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("파일을 찾을 수 없거나 읽을 수 없습니다.");
            }

            return resource;
        } catch (Exception e) {
            throw new RuntimeException("파일 다운로드 중 오류 발생", e);
        }
    }

    // 파일 삭제 메서드
    public boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.deleteIfExists(path); // 파일이 존재하면 삭제
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
