package com.example.demo.controllers;

import com.example.demo.telas.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class TelaGerenteController {

    private final TelaInicial telaInicial;
    private final TelaAgendamentos telaAgendamentos;
    private final TelaAgendarAtendimento telaAgendarAtendimento;
    private final TelaGerenciarProfissionais telaGerenciarProfissionais;
    private final TelaGerenciarServicos telaGerenciarServicos;

    public TelaGerenteController(TelaInicial telaInicial, TelaAgendamentos telaAgendamentos, TelaAgendarAtendimento telaAgendarAtendimento, TelaGerenciarProfissionais telaGerenciarProfissionais, TelaGerenciarServicos telaGerenciarServicos) {
        this.telaInicial = telaInicial;
        this.telaAgendamentos = telaAgendamentos;
        this.telaAgendarAtendimento = telaAgendarAtendimento;
        this.telaGerenciarProfissionais = telaGerenciarProfissionais;
        this.telaGerenciarServicos = telaGerenciarServicos;
    }

    @FXML
    private void onGerenciarProfissionaisButtonClick() {
        telaGerenciarProfissionais.abrir(null);
    }

    @FXML
    private void onGerenciarServicosButtonClick() {
        telaGerenciarServicos.abrir(null);
    }

    @FXML
    private void onGerarRelatorioButtonClick() {
        System.out.println("Relatório");
    }

    @FXML
    private void onAgendarAtendimentoButtonClick() {
        telaAgendarAtendimento.abrir(null);
    }

    @FXML
    private void onConsultarAgendamentosButtonClick() {
        telaAgendamentos.abrir(null);
    }

    @FXML
    private void onSairButtonClick() {
        telaInicial.abrir(null);
    }

}
