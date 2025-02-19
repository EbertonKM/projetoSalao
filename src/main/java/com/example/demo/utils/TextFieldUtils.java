package com.example.demo.utils;

import javafx.scene.control.TextField;

public final class TextFieldUtils {

    public static void impedirEspacos(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\s*")) {
                textField.setText(newValue.replaceAll("\s", ""));
            }
        });
    }

    public static void apenasNumeros(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

    public static void definirRegexEmail(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                textField.setStyle("-fx-border-color: #f14444;"); // Destaca o erro
            } else {
                textField.setStyle(""); // Remove o destaque se estiver correto
            }
        });
    }
}
