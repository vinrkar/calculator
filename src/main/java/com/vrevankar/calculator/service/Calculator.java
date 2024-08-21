package com.vrevankar.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public int calculate(Character op, int num1, int num2) throws IllegalArgumentException {
        try {
            return Operation.of(op).operate(num1, num2);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

}
