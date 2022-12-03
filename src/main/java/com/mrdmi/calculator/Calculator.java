package com.mrdmi.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Calculator {
    private final static MathContext MC = new MathContext(20, RoundingMode.HALF_UP);
    private final static BigDecimal ONE = new BigDecimal(1);
    public String binaryOp(String number1, String number2, String operator) throws ArithmeticException{
        BigDecimal num1 = new BigDecimal(number1);
        BigDecimal num2 = new BigDecimal(number2);

        switch (operator) {
            case "+":
                return format(num1.add(num2, MC));
            case "-":
                return format(num1.subtract(num2, MC));
            case "✕":
                return format(num1.multiply(num2, MC));
            case "/":
                return format(num1.multiply(ONE.divide(num2, MC), MC));
        }

        System.out.println("Unknown operator - " + operator);
        return "0";
    }

    public String unaryOp(String number, String op) {
        BigDecimal num = new BigDecimal(number);

        if ("√".equals(op)) {
            return format(num.sqrt(MC));
        }
        return "0";
    }

    private String format(BigDecimal d) {
        System.out.println(d + " n");
        DecimalFormatSymbols fs = DecimalFormatSymbols.getInstance();
        fs.setDecimalSeparator('.');
        DecimalFormat simple = new DecimalFormat("#.##################", fs);
        DecimalFormat scientific = new DecimalFormat("0.##############E0", fs);
        DecimalFormat scientificNeg = new DecimalFormat("0.#############E0", fs);

        if (d.compareTo(new BigDecimal("9999999999999999999.0")) > 0 ||
                d.compareTo(new BigDecimal("-9999999999999999999.0")) < 0 ||
                (d.compareTo(new BigDecimal("0.00001")) < 0 && d.compareTo(new BigDecimal("-0.0001")) > 0)) {
            if (d.compareTo(new BigDecimal("0")) > 0)
                return scientific.format(d);
            else
                return scientificNeg.format(d);
        } else {
            System.out.println("x");
            String res = simple.format(d);
            return res.length() > 20 ? res.substring(0, 20) : res;
        }
    }
}
