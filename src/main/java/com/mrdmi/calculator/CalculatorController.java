package com.mrdmi.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CalculatorController implements Screen{

    @FXML
    private TextField output;

    private final Model handler;

    public CalculatorController() {

        this.handler = new Model(this);
    }

    @FXML
    private void buttonPressed(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        processEvent(value);
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getText().matches("[0-9-+/]"))
            processEvent(keyEvent.getText());
        else if (keyEvent.getCode() == KeyCode.BACK_SPACE)
            processEvent("⌫");
        else if (keyEvent.getText().equals("*"))
            processEvent("✕");
    }

    private void processEvent(String value) {
        handler.handleInput(value);
    }

    @Override
    public void updateScreen(String output) {
        this.output.setText(output);
    }

}