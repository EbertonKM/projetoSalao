package com.example.demo.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public final class ConfirmacaoUtils {
    private ConfirmacaoUtils() {}

    public static boolean confirmacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}
