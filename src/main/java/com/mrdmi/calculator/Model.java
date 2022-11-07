package com.mrdmi.calculator;


public class Model {

    private final Screen screen;
    private final Calculator calculator = new Calculator();
    private boolean isFloat = false;
    private boolean operationFlag = false;
    private boolean isOperationDone = false;
    private static final String DEFAULT_CURR_VAL = "0";
    private String currentNum = DEFAULT_CURR_VAL;
    private String register = "";
    private String operator = "";
    private String tmp = "";
    public Model(Screen screen) {
        this.screen = screen;
    }

    public void handleInput(String val) {

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
        if (register.isEmpty())
            return;

        if (!operationFlag) {
            tmp = currentNum;
            operationFlag = true;
        }
        double arg1 = Double.parseDouble(register);
        double arg2 = Double.parseDouble(tmp);

        currentNum = calculator.calculate(arg1, arg2, operator).toString();

        register = currentNum;

        isOperationDone = true;
    }

    private void writeOperator(String val) {

        if (register.isEmpty()) {
            register = currentNum;
            operationFlag = true;
        } else
            handleOperation();

        operator = val;
        isOperationDone = false;
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

        if (isOperationDone) {
            clear();
        }

        if (operationFlag) {
            resetCurrentNum();
            operationFlag = false;
        }

        if (val.equals(".")) {
            if (!isFloat) {
                currentNum += val;
                isFloat = true;
            }
            return;
        }

        if (currentNum.equals("0")) {
            currentNum = val;
            return;
        }

        currentNum += val;
    }

    private void resetCurrentNum() {
        currentNum = DEFAULT_CURR_VAL;
        isFloat = false;
    }
}
