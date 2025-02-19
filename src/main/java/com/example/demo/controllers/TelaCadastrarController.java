package com.example.demo.controllers;

import com.example.demo.entities.Cliente;
import com.example.demo.services.ContaService;
import com.example.demo.telas.TelaInicial;
import com.example.demo.utils.TextFieldUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private final TelaInicial telaInicial;

    public TelaCadastrarController(ContaService contaService, TelaInicial telaInicial) {
        this.contaService = contaService;
        this.telaInicial = telaInicial;
    }

    @FXML
    public void initialize() {
        //impede que espaços sejam digitados nos campos de e-mail e senha
        TextFieldUtils.impedirEspacos(emailInputTextField);
        TextFieldUtils.impedirEspacos(senhaInputTextField);
        TextFieldUtils.impedirEspacos(confirmarSenhaInputTextField);
        //impede que caracteres além de números sejam digitados no campo de telefone
        TextFieldUtils.apenasNumeros(telefoneInputTextField);
        //define regex para o e-mail
        TextFieldUtils.definirRegexEmail(emailInputTextField);
    }

    @FXML
    private void onCadastrarButtonClick(){
        if(camposVazios()) {
            System.out.println("CAMPOS VAZIOS, TODOS PRECISAM SER PREENCHIDOS");
        }else if(!senhasBatem()) {
            System.out.println("SENHAS NAO COINCIDEM");
        }else if(!emailDisponivel()) {
            System.out.println("EMAIL INDISPONÍVEL");
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(nomeInputTextField.getText());
            cliente.setEmail(emailInputTextField.getText());
            cliente.setTelefone(telefoneInputTextField.getText());
            cliente.setSenha(senhaInputTextField.getText());
            cliente.setCargo(contaService.cargoCliente());
            contaService.salvar(cliente);
            System.out.println("CLIENTE SALVO NO BANCO");
        }
    }

    @FXML
    private void onVoltarButtonClick() {
        Stage stage = (Stage) nomeInputTextField.getScene().getWindow();
        telaInicial.abrir(stage);
    }

    private boolean senhasBatem() {
        return senhaInputTextField.getText().equals(confirmarSenhaInputTextField.getText());
    }

    private boolean emailDisponivel() {
        return contaService.emailDisponivel(emailInputTextField.getText());
    }

    private boolean camposVazios() {
        return nomeInputTextField.getText().isEmpty() ||
                emailInputTextField.getText().isEmpty() ||
                senhaInputTextField.getText().isEmpty() ||
                confirmarSenhaInputTextField.getText().isEmpty();
    }
}
