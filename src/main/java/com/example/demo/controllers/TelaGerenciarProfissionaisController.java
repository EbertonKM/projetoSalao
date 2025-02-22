package com.example.demo.controllers;

import com.example.demo.entities.Pessoa;
import com.example.demo.entities.Profissional;
import com.example.demo.entities.Servico;
import com.example.demo.repositories.PessoaRepository;
import com.example.demo.repositories.ServicoRepository;
import com.example.demo.services.ContaService;
import com.example.demo.telas.TelaGerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TelaGerenciarProfissionaisController {

    @FXML
    private ListView<String> tabelaProfissionais;

    private final TelaGerente telaGerente;
    private final PessoaRepository pessoaRepository;
    private final ContaService contaService;

    public TelaGerenciarProfissionaisController(TelaGerente telaGerente, PessoaRepository pessoaRepository, ContaService contaService) {
        this.telaGerente = telaGerente;
        this.pessoaRepository = pessoaRepository;
        this.contaService = contaService;
    }

    @FXML
    public void initialize() {
        popularLista();
    }

    @FXML
    private void onNovoButtonClick() {}

    @FXML
    private void onExcluirButtonClick() {}

    @FXML
    private void onVoltarButtonClick() {
        telaGerente.abrir(null);
    }

    private void popularLista() {
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Pessoa> profissionais = pessoaRepository.getAllByCargo(contaService.cargoProfissional());
        for(Pessoa p : profissionais) {
            items.add(p.getNome());
        }
        tabelaProfissionais.setItems(items);
    }
}
