package com.example.demo.controllers;

import com.example.demo.entities.Cliente;
import com.example.demo.services.ContaService;
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

    private final ContaService contaService;

    public TelaCadastrarController(ContaService contaService) {
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
        if(camposPreenchidos()) {
            System.out.println("CAMPOS VAZIOS, NOME EMAIL E SENHA PRECISAM SER PREENCHIDOS");
        }else if(senhasBatem()) {
            if(emailDisponivel()) {
                Cliente cliente = new Cliente();
                cliente.setNome(nomeInputTextField.getText());
                cliente.setEmail(emailInputTextField.getText());
                cliente.setTelefone(telefoneInputTextField.getText());
                cliente.setSenha(senhaInputTextField.getText());
                cliente.setCargo(contaService.cargoCliente());
                contaService.salvar(cliente);
                System.out.println("CLIENTE SALVO NO BANCO");
            } else
                System.out.println("EMAIL INDISPONÍVEL");
        }else
            System.out.println("SENHAS NAO COINCIDEM");
    }

    private boolean senhasBatem() {
        return senhaInputTextField.getText().equals(confirmarSenhaInputTextField.getText());
    }

    private boolean emailDisponivel() {
        return contaService.emailDisponivel(emailInputTextField.getText());
    }

    private boolean camposPreenchidos() {
        return !nomeInputTextField.getText().isEmpty() &&
                !emailInputTextField.getText().isEmpty() &&
                !senhaInputTextField.getText().isEmpty() &&
                !confirmarSenhaInputTextField.getText().isEmpty();
    }
}
