package com.example.demo.controllers;

import com.example.demo.telas.TelaAgendamentos;
import com.example.demo.telas.TelaAgendarAtendimento;
import com.example.demo.telas.TelaInicial;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class TelaClienteController {

    private final TelaInicial telaInicial;
    private final TelaAgendamentos telaAgendamentos;
    private final TelaAgendarAtendimento telaAgendarAtendimento;

    public TelaClienteController(TelaInicial telaInicial, TelaAgendamentos telaAgendamentos,
                                 TelaAgendarAtendimento telaAgendarAtendimento) {
        this.telaInicial = telaInicial;
        this.telaAgendamentos = telaAgendamentos;
        this.telaAgendarAtendimento = telaAgendarAtendimento;
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
