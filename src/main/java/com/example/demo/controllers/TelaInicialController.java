package com.example.demo.controllers;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Gerente;
import com.example.demo.entities.Pessoa;
import com.example.demo.entities.Profissional;
import com.example.demo.services.ContaService;
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
    private final ContaService contaService;

    public TelaInicialController(TelaCadastrar telaCadastrar, ContaService contaService) {
        this.telaCadastrar = telaCadastrar;
        this.contaService = contaService;
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
            Optional<Pessoa> pessoa = contaService.validarLogin(emailInputTextField.getText(), senhaInputTextField.getText());
            if(pessoa.isPresent()) {
                Pessoa pessoaLogin = pessoa.get();
                if(pessoaLogin instanceof Cliente) {
                    //abrir painel Cliente
                    System.out.println("Login Cliente");
                }else if(pessoaLogin instanceof Profissional) {
                    //abrir painel Profissional
                    System.out.println("Login Profissional");
                }else if(pessoaLogin instanceof Gerente) {
                    //abrir painel Gerente
                    System.out.println("Login Gerente");
                }
            }else {
                System.out.println("CREDENCIAIS INVÁLIDAS");
            }
        }else {
            System.out.println("CAMPOS DE LOGIN VAZIOS");
        }
    }
    @FXML
    private void onAbrirCadastrarButtonClick() {
        telaCadastrar.abrir();
    }
}
