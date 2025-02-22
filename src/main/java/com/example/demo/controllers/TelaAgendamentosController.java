package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.telas.TelaCliente;
import com.example.demo.telas.TelaGerente;
import com.example.demo.telas.TelaProfissional;
import com.example.demo.utils.ControleLogin;
import com.example.demo.utils.NavegacaoUtils;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class TelaAgendamentosController {

    @FXML
    private TableView<Atendimento> tabelaAgendamentos;

    private final TelaCliente telaCliente;
    private final TelaProfissional telaProfissional;
    private final TelaGerente telaGerente;

    public TelaAgendamentosController(TelaCliente telaCliente,
                                      TelaProfissional telaProfissional,
                                      TelaGerente telaGerente) {
        this.telaCliente = telaCliente;
        this.telaProfissional = telaProfissional;
        this.telaGerente = telaGerente;
    }

    @FXML
    private TextArea text;

    @FXML
    public void initialize() {
        switch (ControleLogin.getLogin()) {
            case Cliente cliente -> {
                tabelaAgendamentosCliente();
            }
            case Profissional profissional -> {
                tabelaAgendamentosProfissional();
            }
            case Gerente gerente -> {
                tabelaAgendamentosGerente();
            }
            default -> {throw new RuntimeException("Nenhum login encontrado");}
        }
    }

    @FXML
    private void onVoltarButtonClick() {
        NavegacaoUtils.voltarPainel(telaCliente, telaProfissional, telaGerente);
    }

    private void tabelaAgendamentosCliente() {
        TableColumn<Atendimento, String> colunaServico = new TableColumn<>("Serviço");
        colunaServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        TableColumn<Atendimento, LocalDateTime> colunaHorario = new TableColumn<>("Horário");
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        TableColumn<Atendimento, String> colunaStatus = new TableColumn<>("Status");
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tabelaAgendamentos.getColumns().addAll(colunaServico, colunaHorario, colunaStatus);

        Servico servico = new Servico(1, "Banho e tosa", 10);
        Profissional profissional = new Profissional();
        Cliente cliente = new Cliente();
        ObservableList<Atendimento> atendimentos = FXCollections.observableArrayList(
                new Atendimento(1, Status.AGENDADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(2, Status.CANCELADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(3, Status.REALIZADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(1, Status.AGENDADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(2, Status.CANCELADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(3, Status.REALIZADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(1, Status.AGENDADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(2, Status.CANCELADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(3, Status.REALIZADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(1, Status.AGENDADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(2, Status.CANCELADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(3, Status.REALIZADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(1, Status.AGENDADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(2, Status.CANCELADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(3, Status.REALIZADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(1, Status.AGENDADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(2, Status.CANCELADO, LocalDateTime.now(), 2, servico, profissional, cliente),
                new Atendimento(3, Status.REALIZADO, LocalDateTime.now(), 2, servico, profissional, cliente)
        );

        tabelaAgendamentos.setItems(atendimentos);
    }

    private void tabelaAgendamentosProfissional() {

    }

    private void tabelaAgendamentosGerente() {

    }
}
