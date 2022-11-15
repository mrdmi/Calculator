package com.mrdmi.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {
    public String calculate(String number1, String number2, String operator) throws ArithmeticException{
        BigDecimal num1 = new BigDecimal(number1);
        BigDecimal num2 = new BigDecimal(number2);
        MathContext mc = new MathContext(12, RoundingMode.HALF_UP);
        BigDecimal one = new BigDecimal(1);

        switch (operator) {
            case "+":
                return num1.add(num2, mc).toString();
            case "-":
                return num1.subtract(num2, mc).toString();
            case "✕":
                return num1.multiply(num2, mc).toString();
            case "/":
                return num1.multiply(one.divide(num2, mc), mc).toString();
        }

        System.out.println("Unknown operator - " + operator);
        return "0";
    }
}
