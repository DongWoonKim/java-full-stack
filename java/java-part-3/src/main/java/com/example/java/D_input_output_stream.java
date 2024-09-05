package com.example.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class D_input_output_stream {
    public static void main(String[] args) {
        // 1. 바탕화면 경로 가져오기
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        // 2. '가계부' 폴더 경로 설정
        String accountBookPath = desktopPath + File.separator + "가계부";

        // 3. '가계부' 폴더가 없으면 생성
        Path accountBookFolder = Paths.get(accountBookPath);
        try {
            if (Files.notExists(accountBookFolder)) {
                Files.createDirectory(accountBookFolder);
                System.out.println("'가계부' 폴더를 생성했습니다.");
            } else {
                System.out.println("'가계부' 폴더가 이미 존재합니다.");
            }

            // 4. 오늘 날짜의 파일 경로 설정 (yyyy-MM-dd 형식)
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path todayFile = accountBookFolder.resolve(today + ".txt");

            // 5. 오늘 날짜의 파일을 생성하고 "Hello World"를 넣음
            if (Files.notExists(todayFile)) {
                try (FileOutputStream fos = new FileOutputStream(todayFile.toFile())) {
                    String content = "Hello World";
                    fos.write(content.getBytes());
                    System.out.println(today + ".txt 파일을 생성하고 내용을 썼습니다.");
                }
            } else {
                System.out.println(today + ".txt 파일이 이미 존재합니다.");
            }

            // 6. 오늘 날짜의 파일을 읽어오기
            if (Files.exists(todayFile)) {
                try (FileInputStream fis = new FileInputStream(todayFile.toFile())) {
                    int byteData;
                    System.out.print("Reading from " + today + ".txt: ");
                    while ((byteData = fis.read()) != -1) {
                        System.out.print((char) byteData);
                    }
                    System.out.println(); // 줄바꿈
                }
            } else {
                System.out.println(today + ".txt 파일이 존재하지 않습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
