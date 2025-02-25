package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.repositories.AtendimentoRepository;
import com.example.demo.telas.TelaCliente;
import com.example.demo.telas.TelaGerente;
import com.example.demo.telas.TelaProfissional;
import com.example.demo.utils.ControleLogin;
import com.example.demo.utils.NavegacaoUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TelaAgendamentosController {

    private final AtendimentoRepository atendimentoRepository;
    @FXML
    private TableView<Atendimento> tabelaAgendamentos;

    private final TelaCliente telaCliente;
    private final TelaProfissional telaProfissional;
    private final TelaGerente telaGerente;

    public TelaAgendamentosController(TelaCliente telaCliente,
                                      TelaProfissional telaProfissional,
                                      TelaGerente telaGerente, AtendimentoRepository atendimentoRepository) {
        this.telaCliente = telaCliente;
        this.telaProfissional = telaProfissional;
        this.telaGerente = telaGerente;
        this.atendimentoRepository = atendimentoRepository;
    }

    @FXML
    private TextArea text;

    @FXML
    public void initialize() {
        TableColumn<Atendimento, String> colunaServico = new TableColumn<>("Serviço");
        colunaServico.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getServico().getDescricao()));

        TableColumn<Atendimento, LocalDateTime> colunaHorario = new TableColumn<>("Horário");
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));

        TableColumn<Atendimento, String> colunaStatus = new TableColumn<>("Status");
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tabelaAgendamentos.getColumns().addAll(colunaServico, colunaHorario, colunaStatus);

        List<Atendimento> atendimentosList = new ArrayList<>();
        Pessoa login = ControleLogin.getLogin();

        if(login instanceof Cliente) {
            atendimentosList = atendimentoRepository.findAllByCliente((Cliente) login);
        } else if(login instanceof Profissional) {
            atendimentosList = atendimentoRepository.findAllByProfissional((Profissional) login);
        } else if(login instanceof Gerente) {
            atendimentosList = atendimentoRepository.findAll();
        }

        ObservableList<Atendimento> atendimentos = FXCollections.observableArrayList(atendimentosList);
        tabelaAgendamentos.setItems(atendimentos);
    }


    @FXML
    private void onVoltarButtonClick() {
        NavegacaoUtils.voltarPainel(telaCliente, telaProfissional, telaGerente);
    }
}
