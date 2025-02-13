package com.example.demo.controllers;

import com.example.demo.entities.Pessoa;
import com.example.demo.services.LoginService;
import com.example.demo.telas.TelaCadastrar;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class TelaInicialController {
    @FXML
    private TextField emailInputTextField;
    @FXML
    private TextField senhaInputTextField;

    private final TelaCadastrar telaCadastrar;
    private final LoginService loginService;

    public TelaInicialController(TelaCadastrar telaCadastrar, LoginService loginService) {
        this.telaCadastrar = telaCadastrar;
        this.loginService = loginService;
    }

    @FXML
    public void initialize() {
        //impede que espaços sejam digitados nos campos de senha
        senhaInputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\s*")) {
                senhaInputTextField.setText(newValue.replaceAll("\s", ""));
            }
        });
    }

    @FXML
    private void onLoginButtonClick() {
        if(!emailInputTextField.getText().isEmpty() || !senhaInputTextField.getText().isEmpty()) {
            Optional<Pessoa> pessoa = loginService.validarLogin(emailInputTextField.getText(), senhaInputTextField.getText());
            System.out.println("GET NOME NAO TA FUNFANDO");
        }else {
            System.out.println("CAMPOS DE LOGIN VAZIOS");
        }
    }
    @FXML
    private void onAbrirCadastrarButtonClick() {
        telaCadastrar.abrir();
    }
}
