package com.example.tobi.springtobi.ch03.ex_3_5.calc.template_v2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String filepath) throws IOException {
        BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader reader) throws IOException {
                Integer sum = 0;
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sum += Integer.valueOf(line);
                }

                return sum;
            }
        };

        return fileReadTemplate(filepath, sumCallback);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader reader) throws IOException {
                Integer multiply = 1;
                String line = null;
                while ((line = reader.readLine()) != null) {
                    multiply *= Integer.valueOf(line);
                }

                return multiply;
            }
        };

        return fileReadTemplate(filepath, multiplyCallback);
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callback.doSomethingWithReader(br);

            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(br != null) {
                br.close();
            }
        }

    }

}
