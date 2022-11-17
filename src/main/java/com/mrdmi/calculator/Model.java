package com.mrdmi.calculator;


public class Model {

    private final Screen screen;
    private final Calculator calculator = new Calculator();
    private boolean isFloat = false;
    private boolean operationFlag = false;
    private boolean isOperationDone = false;
    private boolean anotherOpFlag = false;
    private static final String DEFAULT_CURR_VAL = "0";
    private String currentNum = DEFAULT_CURR_VAL;
    private String register = "";
    private String operator = "";
    private String tmp = "";
    public Model(Screen screen) {
        this.screen = screen;
    }

    public void handleInput(String val) {

        if (currentNum.equals("Number out of range") || currentNum.equals("Can't divide by zero"))
            clear();

        if (val.equals("+/-") && !currentNum.equals("0")) {
            if (currentNum.charAt(0) == '-') {
                currentNum = currentNum.replaceFirst("-", "");
            } else
                currentNum = "-" + currentNum;
        }

        if ((val.equals("⌫")) && !operationFlag) {
            int len = currentNum.length();
            if (len == 1)
                currentNum = "0";
            else currentNum = currentNum.substring(0, len - 1);
        }

        if (val.equals("C"))
            clear();

        if (val.matches("[0-9.]")) {
            writeCurrentNum(val);
        }

        if (val.matches("[-+/✕]")) {
            writeOperator(val);
        }

        if (val.equals("=")) {
            handleOperation();
        }

        screen.updateScreen(currentNum);
        System.out.println(register);
    }

    private void handleOperation() {

        if (anotherOpFlag)
            return;

        if (register.isEmpty())
            return;

        if (!operationFlag) {
            tmp = currentNum;
            operationFlag = true;
        }

        isOperationDone = true;

        try {
            String res = calculator.calculate(register, tmp, operator);
            System.out.println(res);
            if (res.contains("E") && Integer.parseInt(res.substring(res.indexOf('E') + 2)) > 99) {
                currentNum = "Number out of range";
                register = currentNum;
                return;
            }
            currentNum = res;
            register = currentNum;
        } catch (ArithmeticException e) {
            currentNum = "Can't divide by zero";
        }
    }

    private void writeOperator(String val) {
        if (register.isEmpty()) {
            register = currentNum;
            operationFlag = true;
        } else if (!operationFlag)
            handleOperation();

        operator = val;
        isOperationDone = false;
        anotherOpFlag = true;
    }

    private void clear() {
        isFloat = false;
        operationFlag = false;
        currentNum = DEFAULT_CURR_VAL;
        register = "";
        operator = "";
        tmp = "";
        isOperationDone = false;
    }

    private void writeCurrentNum(String val) {
        anotherOpFlag = false;

        if (isOperationDone) {
            clear();
        }

        if (operationFlag) {
            resetCurrentNum();
            operationFlag = false;
        }

        if (val.equals(".")) {
            if (!isFloat && currentNum.length() < 18) {
                currentNum += val;
                isFloat = true;
            }
            return;
        }

        if (currentNum.equals("0")) {
            currentNum = val;
            return;
        }

        if (currentNum.length() >= 19)
            return;

        currentNum += val;

    }

    private void resetCurrentNum() {
        currentNum = DEFAULT_CURR_VAL;
        isFloat = false;
    }
}