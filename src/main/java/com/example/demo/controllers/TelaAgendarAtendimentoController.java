package com.example.demo.controllers;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Gerente;
import com.example.demo.entities.Profissional;
import com.example.demo.entities.Servico;
import com.example.demo.repositories.ServicoRepository;
import com.example.demo.telas.TelaAgendarAtendimento;
import com.example.demo.telas.TelaCliente;
import com.example.demo.telas.TelaGerente;
import com.example.demo.telas.TelaProfissional;
import com.example.demo.utils.ControleLogin;
import com.example.demo.utils.NavegacaoUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TelaAgendarAtendimentoController {

    private final ServicoRepository servicoRepository;
    @FXML
    private ComboBox<Servico> servicoComboBox;
    @FXML
    private Label labelCliente;
    @FXML
    private ComboBox<Cliente> clienteComboBox;
    @FXML
    private DatePicker dataDatePicker;
    @FXML
    private ComboBox<String> horarioComboBox;

    private final TelaCliente telaCliente;
    private final TelaProfissional telaProfissional;
    private final TelaGerente telaGerente;

    public TelaAgendarAtendimentoController(TelaCliente telaCliente, TelaProfissional telaProfissional, TelaGerente telaGerente, ServicoRepository servicoRepository) {
        this.telaCliente = telaCliente;
        this.telaProfissional = telaProfissional;
        this.telaGerente = telaGerente;
        this.servicoRepository = servicoRepository;
    }

    @FXML
    public void initialize() {
        dataDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if(item.isBefore(LocalDate.now())) {
                    setDisable(true);
                }
                if(item.getDayOfWeek() == DayOfWeek.SATURDAY || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    setDisable(true);
                }
            }
        });
        servicoComboBox.setConverter(new StringConverter<Servico>() {
            @Override
            public String toString(Servico servico) {
                return servico != null ? servico.getDescricao() : "";
            }
            @Override
            public Servico fromString(String string) {
                Optional<Servico> servico = servicoRepository.findByDescricao(string);
                return servico.orElse(null);
            }
        });
        List<Servico> servicos = servicoRepository.findAllByAtivo(1);
        servicoComboBox.getItems().addAll(servicos);
    }

    @FXML
    private void onAgendarButtonClick() {}

    @FXML
    private void onVoltarButtonClick() {
        NavegacaoUtils.voltarPainel(telaCliente, telaProfissional, telaGerente);
    }
}
