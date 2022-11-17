package com.mrdmi.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {
    private final static MathContext MC = new MathContext(10, RoundingMode.HALF_UP);
    private final static BigDecimal ONE = new BigDecimal(1);
    public String binaryOp(String number1, String number2, String operator) throws ArithmeticException{
        BigDecimal num1 = new BigDecimal(number1);
        BigDecimal num2 = new BigDecimal(number2);

        switch (operator) {
            case "+":
                return num1.add(num2, MC).toString();
            case "-":
                return num1.subtract(num2, MC).toString();
            case "✕":
                return num1.multiply(num2, MC).toString();
            case "/":
                return num1.multiply(ONE.divide(num2, MC), MC).toString();
        }

        System.out.println("Unknown operator - " + operator);
        return "0";
    }

    public String unaryOp(String number, String op) {
        BigDecimal num = new BigDecimal(number);

        if ("√".equals(op)) {
            return num.sqrt(MC).toString();
        }
        return "0";
    }
}
