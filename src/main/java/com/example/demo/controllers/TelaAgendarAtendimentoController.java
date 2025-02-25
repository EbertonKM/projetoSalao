package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.repositories.AtendimentoRepository;
import com.example.demo.repositories.PessoaRepository;
import com.example.demo.repositories.ServicoRepository;
import com.example.demo.services.ContaService;
import com.example.demo.services.PessoaService;
import com.example.demo.telas.TelaAgendarAtendimento;
import com.example.demo.telas.TelaCliente;
import com.example.demo.telas.TelaGerente;
import com.example.demo.telas.TelaProfissional;
import com.example.demo.utils.ControleLogin;
import com.example.demo.utils.NavegacaoUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class TelaAgendarAtendimentoController {

    private final ServicoRepository servicoRepository;
    private final PessoaRepository pessoaRepository;
    private final ContaService contaService;
    private final AtendimentoRepository atendimentoRepository;
    @FXML
    private ComboBox<Servico> servicoComboBox;
    @FXML
    private Label labelCliente;
    @FXML
    private ComboBox<Pessoa> clienteComboBox;
    @FXML
    private DatePicker dataDatePicker;
    @FXML
    private ComboBox<String> horarioComboBox;

    private final TelaCliente telaCliente;
    private final TelaProfissional telaProfissional;
    private final TelaGerente telaGerente;

    public TelaAgendarAtendimentoController(TelaCliente telaCliente, TelaProfissional telaProfissional, TelaGerente telaGerente, ServicoRepository servicoRepository, PessoaRepository pessoaRepository, ContaService contaService, AtendimentoRepository atendimentoRepository) {
        this.telaCliente = telaCliente;
        this.telaProfissional = telaProfissional;
        this.telaGerente = telaGerente;
        this.servicoRepository = servicoRepository;
        this.pessoaRepository = pessoaRepository;
        this.contaService = contaService;
        this.atendimentoRepository = atendimentoRepository;
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

        clienteComboBox.setConverter(new StringConverter<Pessoa>() {
            @Override
            public String toString(Pessoa cliente) {
                return cliente != null ? cliente.getNome()+" ["+cliente.getEmail()+"]" : "";
            }
            @Override
            public Pessoa fromString(String string) {
                string = string.split("\\[")[1].split("]")[0];
                Optional<Pessoa> cliente = pessoaRepository.findByEmail(string);
                return cliente.orElse(null);
            }
        });
        tratarClienteComboBox();
        popularComboBoxHorarios();

    }

    @FXML
    private void onAgendarButtonClick() {
        if(campoVazio())
            return;
        LocalDate dataSelecionada = dataDatePicker.getValue();
        String horarioSelecionado = horarioComboBox.getValue();

        int hora = Integer.parseInt(horarioSelecionado.split(":")[0]); // Extrai a hora
        LocalDateTime dataHora = dataSelecionada.atTime(hora, 0); // Cria LocalDateTime com minutos 0
        System.out.println(dataHora);
        Servico servico = servicoComboBox.getValue();
        Optional<Pessoa> profissional = pessoaRepository.findByCargo(contaService.cargoProfissional());
        Profissional profissionalEncontrado;
        profissionalEncontrado = (Profissional) profissional.orElse(null);
        Pessoa clienteCombo = clienteComboBox.getValue();
        Cliente cliente = (Cliente) clienteCombo;
        Atendimento atendimento = new Atendimento(null, Status.AGENDADO, dataHora, servico.getPreco(), servico, profissionalEncontrado, cliente);
        atendimentoRepository.save(atendimento);
    }

    @FXML
    private void onVoltarButtonClick() {
        NavegacaoUtils.voltarPainel(telaCliente, telaProfissional, telaGerente);
    }

    private void tratarClienteComboBox() {
        Pessoa login = ControleLogin.getLogin();
        if(login instanceof Cliente) {
            clienteComboBox.getItems().add(login);
            clienteComboBox.setValue(login);
            labelCliente.setVisible(false);
            clienteComboBox.setVisible(false);
        }else {
            List<Pessoa> clientes = pessoaRepository.getAllByCargo(contaService.cargoCliente());
            clienteComboBox.getItems().addAll(clientes);
        }
    }

    private void popularComboBoxHorarios() {
        horarioComboBox.getItems().clear();

        for (int hora = 8; hora <= 11; hora++) {
            horarioComboBox.getItems().add(hora + ":00");
        }
        for (int hora = 13; hora <= 17; hora++) {
            horarioComboBox.getItems().add(hora + ":00");
        }
    }

    private boolean campoVazio() {
        return servicoComboBox.getValue() == null || clienteComboBox.getValue().getNome().isEmpty()
                || dataDatePicker.getValue() == null || horarioComboBox.getValue() == null;
    }
}
