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
import com.example.demo.utils.TextFieldUtils;
import javafx.fxml.FXML;
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

    private final TelaClienteController telaClienteController;
    private final TelaGerenteController telaGerenteController;
    private final TelaProfissionalController telaProfissionalController;

    public TelaInicialController(TelaCadastrar telaCadastrar,
                                 ContaService contaService,
                                 TelaCliente telaCliente,
                                 TelaProfissional telaProfissional,
                                 TelaGerente telaGerente,
                                 TelaClienteController telaClienteController,
                                 TelaGerenteController telaGerenteController,
                                 TelaProfissionalController telaProfissionalController) {
        this.telaCadastrar = telaCadastrar;
        this.contaService = contaService;
        this.telaCliente = telaCliente;
        this.telaGerente = telaGerente;
        this.telaProfissional = telaProfissional;
        this.telaClienteController = telaClienteController;
        this.telaGerenteController = telaGerenteController;
        this.telaProfissionalController = telaProfissionalController;
    }

    @FXML
    public void initialize() {
        //impede que espaços sejam digitados nos campos de login
        TextFieldUtils.impedirEspacos(emailInputTextField);
        TextFieldUtils.impedirEspacos(senhaInputTextField);
        //define regex para o e-mail
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
                if(pessoaLogin instanceof Cliente) {
                    //abrir painel Cliente
                    System.out.println("Login Cliente");
                    telaCliente.abrir();
                    telaClienteController.definirLogin(pessoaLogin);
                }else if(pessoaLogin instanceof Profissional) {
                    //abrir painel Profissional
                    System.out.println("Login Profissional");
                    telaProfissional.abrir();
                    telaProfissionalController.definirLogin(pessoaLogin);
                }else if(pessoaLogin instanceof Gerente) {
                    //abrir painel Gerente
                    System.out.println("Login Gerente");
                    telaGerente.abrir();
                    telaGerenteController.definirLogin(pessoaLogin);
                }
            }else {
                System.out.println("CREDENCIAIS INVÁLIDAS");
            }
        }
    }
    @FXML
    private void onAbrirCadastrarButtonClick() {
        Stage stage = (Stage) emailInputTextField.getScene().getWindow();
        telaCadastrar.abrir(stage);
    }

    private boolean camposVazios() {
        return emailInputTextField.getText().isEmpty() || senhaInputTextField.getText().isEmpty();
    }
}
