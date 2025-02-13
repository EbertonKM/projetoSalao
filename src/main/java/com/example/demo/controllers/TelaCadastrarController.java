package com.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class TelaCadastrarController {
    @FXML
    private TextField nomeInputTextField;
    @FXML
    private TextField emailInputTextField;
    @FXML
    private TextField telefoneInputTextField;
    @FXML
    private TextField senhaInputTextField;
    @FXML
    private TextField confirmarSenhaInputTextField;

    @FXML
    public void initialize() {
        //impede que espaços sejam digitados nos campos de senha
        senhaInputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\s*")) {
                senhaInputTextField.setText(newValue.replaceAll("\s", ""));
            }
        });
        confirmarSenhaInputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\s*")) {
                confirmarSenhaInputTextField.setText(newValue.replaceAll("\s", ""));
            }
        });
        //impede que caracteres além de números sejam digitados no campo de telefone
        telefoneInputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                telefoneInputTextField.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

    @FXML
    private void onCadastrarButtonClick(){
        System.out.println("Cadastrado");
    }

    private boolean senhasBatem() {
        return senhaInputTextField.getText().equals(confirmarSenhaInputTextField.getText());
    }
}
