package com.example.demo.controllers;

import com.example.demo.entities.Pessoa;
import com.example.demo.entities.Profissional;
import com.example.demo.entities.Servico;
import com.example.demo.repositories.PessoaRepository;
import com.example.demo.repositories.ServicoRepository;
import com.example.demo.services.ContaService;
import com.example.demo.services.PessoaService;
import com.example.demo.telas.TelaGerente;
import com.example.demo.utils.ConfirmacaoUtils;
import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class TelaGerenciarProfissionaisController {

    @FXML
    private ListView<String> tabelaProfissionais;
    @FXML
    private VBox servicosContainer;

    private Profissional profissionalSelecionado;

    private final TelaGerente telaGerente;
    private final PessoaRepository pessoaRepository;
    private final ContaService contaService;
    private final ServicoRepository servicoRepository;
    private final PessoaService pessoaService;

    public TelaGerenciarProfissionaisController(TelaGerente telaGerente, PessoaRepository pessoaRepository,
                                                ContaService contaService, ServicoRepository servicoRepository, PessoaService pessoaService) {
        this.telaGerente = telaGerente;
        this.pessoaRepository = pessoaRepository;
        this.contaService = contaService;
        this.servicoRepository = servicoRepository;
        this.pessoaService = pessoaService;
    }

    @FXML
    public void initialize() {
        popularProfissionais();
        popularServicos(new ArrayList<>());
        tabelaProfissionais.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String email = newValue.split("\\[")[1].split("]")[0];
                Optional<Profissional> profissionalSelecionado = pessoaService.buscarProfissionalComServicos(email);
                if (profissionalSelecionado.isPresent()) {
                    Profissional profissional = profissionalSelecionado.get();
                    this.profissionalSelecionado = profissional;
                    popularServicos(profissional.getServicos());
                }
            }
        });
    }

    @FXML
    private void onNovoButtonClick() {

    }

    @FXML
    private void onDefinirServicosButtonClick() {
        List<Servico> servicosSelecionados = new ArrayList<>();
        for (var node : servicosContainer.getChildren()) {
            if (node instanceof CheckBox checkBox) {
                if (checkBox.isSelected()) {
                    String descricaoServico = checkBox.getText();
                    Optional<Servico> servico = servicoRepository.findByDescricao(descricaoServico);
                    servico.ifPresent(servicosSelecionados::add);
                }
            }
        }
        List<Servico> servicosAtuais = profissionalSelecionado.getServicos();
        for (Servico servicoSelecionado : servicosSelecionados) {
            if (!servicosAtuais.contains(servicoSelecionado)) {
                servicosAtuais.add(servicoSelecionado);
            }
        }
        servicosAtuais.removeIf(servico -> !servicosSelecionados.contains(servico));
        profissionalSelecionado.setServicos(servicosAtuais);
        pessoaRepository.save(profissionalSelecionado);
    }

    @FXML
    private void onExcluirButtonClick() {
        if(tabelaProfissionais.getSelectionModel().getSelectedItem() == null)
            return;
        if(ConfirmacaoUtils.confirmacao("Exclusão", "Deseja realmente excluir este profissional?")) {
            String nome = tabelaProfissionais.getSelectionModel().getSelectedItem().split(" ")[0];
            Optional<Pessoa> profissional = pessoaRepository.findByNome(nome);
            Pessoa pessoaProfissional;
            if(profissional.isPresent()) {
                pessoaProfissional = profissional.get();
                contaService.setCargo(pessoaProfissional, contaService.cargoCliente());
            }else
                throw new RuntimeException("Profissional da exclusão não foi encontrado");
        }
    }

    @FXML
    private void onVoltarButtonClick() {
        telaGerente.abrir(null);
    }

    private void popularProfissionais() {
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Pessoa> profissionais = pessoaRepository.getAllByCargo(contaService.cargoProfissional());
        for(Pessoa p : profissionais) {
            items.add(p.getNome() +" ["+ p.getEmail()+"]");
        }
        tabelaProfissionais.setItems(items);
    }

    private void popularServicos(List<Servico> servicosList) {
        servicosContainer.getChildren().clear();
        List<String> servicos = new ArrayList<>();
        List<Servico> listServicos = servicoRepository.findAllByAtivo(1);
        for(Servico servico : listServicos) {
            servicos.add(servico.getDescricao());
        }
        for (String servico : servicos) {
            CheckBox checkBox = new CheckBox(servico);
            for (Servico servicoItem: servicosList) {
                if(servicoItem.getDescricao().equals(servico)) {
                    checkBox.setSelected(true);
                }
            }
            servicosContainer.getChildren().add(checkBox);
        }
    }
}
