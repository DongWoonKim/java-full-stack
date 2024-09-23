package com.example.tobi.springtobi.ch03.ex_3_5.calc.template_v4;

public interface LineCallBack<T> {

    T doSomethingWithLine(String line, T value);

}
