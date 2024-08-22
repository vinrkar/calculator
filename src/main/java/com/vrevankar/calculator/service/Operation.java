package com.vrevankar.calculator.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public enum Operation {
    ADD(Integer::sum),
    SUBTRACT((num1, num2) -> num1-num2),
    MULTIPLY((num1, num2) -> num1*num2),
    DIVIDE((num1, num2) -> num1/num2);

    private static Map<Character, Operation> operations =
        new HashMap<Character, Operation>() {{
            put('+', ADD);
            put('-', SUBTRACT);
            put('*', MULTIPLY);
            put('/', DIVIDE);
        }};

    private final BinaryOperator<Integer> operation;

    Operation(BinaryOperator<Integer> operation) {
        this.operation = operation;
    }

    public static Operation of(char op) throws IllegalArgumentException {
        if(operations.containsKey(op)) {
            return operations.get(op);
        }

        throw new IllegalArgumentException();
    }

    public int operate(int num1, int num2) {
        return operation.apply(num1, num2);
    }

}
