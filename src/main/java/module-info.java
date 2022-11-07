module com.mrdmi.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mrdmi.calculator to javafx.fxml;
    exports com.mrdmi.calculator;
}