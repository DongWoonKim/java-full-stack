package com.example.tobi.springtobi.ch03.ex_3_5.calc.template_v1;

import java.io.File;
import java.io.IOException;

public class CalcSumTest {
    public static void main(String[] args) throws IOException {
        CalcSumTest calcSumTest = new CalcSumTest();
        calcSumTest.sumOfNumbers();
    }

    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();

        // 사용자 홈 디렉토리 가져오기
        String userHome = System.getProperty("user.home");

        // 바탕화면 경로 생성
        String desktopPath = userHome + File.separator + "Desktop";

        // 읽어올 파일 이름 설정
        String filename = "numbers.txt";

        int sum = calculator.calcSum(desktopPath + File.separator + filename);

        System.out.println(sum);
    }


}
