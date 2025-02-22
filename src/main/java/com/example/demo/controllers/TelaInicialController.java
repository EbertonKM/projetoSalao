package com.example.demo.controllers;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Gerente;
import com.example.demo.entities.Pessoa;
import com.example.demo.entities.Profissional;
import com.example.demo.services.ContaService;
import com.example.demo.telas.TelaCadastrar;
import com.example.demo.telas.TelaCliente;
import com.example.demo.telas.TelaGerente;
import com.example.demo.telas.TelaProfissional;
import com.example.demo.utils.ControleLogin;
import com.example.demo.utils.TextFieldUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private final TelaCliente telaCliente;
    private final TelaGerente telaGerente;
    private final TelaProfissional telaProfissional;

    public TelaInicialController(TelaCadastrar telaCadastrar,
                                 ContaService contaService,
                                 TelaCliente telaCliente,
                                 TelaProfissional telaProfissional,
                                 TelaGerente telaGerente) {
        this.telaCadastrar = telaCadastrar;
        this.contaService = contaService;
        this.telaCliente = telaCliente;
        this.telaGerente = telaGerente;
        this.telaProfissional = telaProfissional;
    }

    @FXML
    public void initialize() {
        ControleLogin.logout();
        TextFieldUtils.impedirEspacos(emailInputTextField);
        TextFieldUtils.impedirEspacos(senhaInputTextField);
        TextFieldUtils.definirRegexEmail(emailInputTextField);
    }

    @FXML
    private void onLoginButtonClick() {
        if(camposVazios()) {
            System.out.println("CAMPOS DE LOGIN VAZIOS");
        }else {
            Optional<Pessoa> pessoa = contaService.validarLogin(emailInputTextField.getText(), senhaInputTextField.getText());
            if(pessoa.isPresent()) {
                Pessoa pessoaLogin = pessoa.get();
                ControleLogin.definirLogin(pessoaLogin);
                switch (pessoaLogin) {
                    case Cliente cliente -> {
                        telaCliente.abrir(null);
                    }
                    case Profissional profissional -> {
                        telaProfissional.abrir(null);
                    }
                    case Gerente gerente -> {
                        telaGerente.abrir(null);
                    }
                    default -> {
                    }
                }
            }else {
                System.out.println("CREDENCIAIS INVÁLIDAS");
            }
        }
    }
    @FXML
    private void onAbrirCadastrarButtonClick() {
        telaCadastrar.abrir(null);
    }

    private boolean camposVazios() {
        return emailInputTextField.getText().isEmpty() || senhaInputTextField.getText().isEmpty();
    }

    //APAGAR ISSO AQUI DEPOIS E NAO ESQUECER DE APAGAR DO XML TAMBÉM !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    private void clienteLogin(){
        Optional<Pessoa> pessoa = contaService.validarLogin("cliente@gmail.com", "cliente");
        ControleLogin.definirLogin(pessoa.get());
        telaCliente.abrir(null);
    }
    @FXML
    private void profissionalLogin(){
        Optional<Pessoa> pessoa = contaService.validarLogin("profissional@gmail.com", "profissional");
        ControleLogin.definirLogin(pessoa.get());
        telaProfissional.abrir(null);
    }
    @FXML
    private void gerenteLogin(){
        Optional<Pessoa> pessoa = contaService.validarLogin("gerente@gmail.com", "gerente");
        ControleLogin.definirLogin(pessoa.get());
        telaGerente.abrir(null);
    } //APAGAR ATÉ AQUI !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
