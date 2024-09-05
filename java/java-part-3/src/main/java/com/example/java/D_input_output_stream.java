package com.example.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class D_input_output_stream {
    public static void main(String[] args) {
        // 사용자 바탕화면 경로 가져오기
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        String outputFile = desktopPath + File.separator + "output.txt";
        String data = "Hello World";

        // 1. 파일에 쓰기
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            // "Hello World"를 output.txt에 쓰기
            fos.write(data.getBytes());
            System.out.println("쓰기 " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 파일에서 읽기
        try (FileInputStream fis = new FileInputStream(outputFile)) {
            int byteData;
            System.out.print("읽기");
            while ((byteData = fis.read()) != -1) {
                // 파일에서 읽은 바이트 데이터를 문자로 변환하여 출력
                System.out.print((char) byteData);
            }
            System.out.println(); // 줄바꿈
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
