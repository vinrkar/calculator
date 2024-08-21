package com.vrevankar.calculator.service;

import org.junit.Rule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.vrevankar.calculator.service.CalculatorTest.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @InjectMocks
    private Calculator calculator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @ParameterizedTest
    @ArgumentsSource(DataProvider.class)
    void calculate(char op, int num1, int num2, int result) {
        assertEquals(result, calculator.calculate(op, num1, num2));
    }

    @ParameterizedTest
    @ArgumentsSource(DataProviderEx.class)
    void calculateEx(char op, int num1, int num2) {
        assertThrows(IllegalArgumentException.class,
            () -> {
                calculator.calculate(op, num1, num2);
            });
    }

    public static class DataProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(ADD, NUM1, NUM2, 10),
                Arguments.of(SUBTRACT, NUM1, NUM2,  6),
                Arguments.of(MULTIPLY, NUM1, NUM2, 16),
                Arguments.of(DIVIDE, NUM1, NUM2,  4)
            );
        }
    }

    public static class DataProviderEx implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of('#', NUM1, NUM2),
                Arguments.of('_', NUM1, NUM2),
                Arguments.of('x', NUM1, NUM2),
                Arguments.of('÷', NUM1, NUM2)
            );
        }
    }
    
    interface TestData {
        int NUM1 = 8;
        int NUM2 = 2;
        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';
    }

}