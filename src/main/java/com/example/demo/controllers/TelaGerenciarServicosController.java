package com.example.demo.controllers;

import com.example.demo.entities.Pessoa;
import com.example.demo.entities.Servico;
import com.example.demo.repositories.PessoaRepository;
import com.example.demo.repositories.ServicoRepository;
import com.example.demo.services.ContaService;
import com.example.demo.telas.TelaGerente;
import com.example.demo.utils.ConfirmacaoUtils;
import com.example.demo.utils.TextFieldUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TelaGerenciarServicosController {


    @FXML
    private ListView<String> tabelaServicos;
    @FXML
    private TextField novoPrecoTextField;
    @FXML
    private TextField descricaoTextField;
    @FXML
    private TextField precoTextField;

    private Servico servicoSelecionado = null;

    private final TelaGerente telaGerente;
    private final ServicoRepository servicoRepository;

    public TelaGerenciarServicosController(TelaGerente telaGerente, ServicoRepository servicoRepository) {
        this.telaGerente = telaGerente;
        this.servicoRepository = servicoRepository;
    }

    @FXML
    public void initialize() {
        TextFieldUtils.definirRegexDinheiro(novoPrecoTextField);
        TextFieldUtils.definirRegexDinheiro(precoTextField);
        popularServicos();
        tabelaServicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                servicoSelecionado = atualizarSelecao();
                if(servicoSelecionado != null) {
                    novoPrecoTextField.setText(String.valueOf(servicoSelecionado.getPreco()));
                }
            }
        });
    }

    @FXML
    private void onNovoButtonClick() {
        if(descricaoTextField.getText().isEmpty() || precoTextField.getText().isEmpty())
            return;
        Servico servico = new Servico(null, descricaoTextField.getText(), getInputPreco(precoTextField), 1);
        servicoRepository.save(servico);
        descricaoTextField.clear();
        precoTextField.clear();
        novoPrecoTextField.clear();
        popularServicos();

    }

    @FXML
    private void onDefinirPrecoButtonClick() {
        if(servicoSelecionado == null)
            return;
        if(ConfirmacaoUtils.confirmacao("Alterar preço", "Deseja alterar o valor do serviço "
                + servicoSelecionado.getDescricao() + " para R$" + novoPrecoTextField.getText())) {
            servicoSelecionado.setPreco(getInputPreco(novoPrecoTextField));
            servicoRepository.save(servicoSelecionado);
            novoPrecoTextField.clear();
            popularServicos();
        }
    }

    @FXML
    private void onExcluirButtonClick() {
        if(servicoSelecionado == null)
            return;
        if(ConfirmacaoUtils.confirmacao("Exclusão", "Deseja realmente excluir este serviço?")) {
            servicoSelecionado.setAtivo(0);
            servicoRepository.save(servicoSelecionado);
        }
    }

    @FXML
    private void onVoltarButtonClick() {
        telaGerente.abrir(null);
    }

    private void popularServicos() {
        tabelaServicos.getItems().clear();
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Servico> servicos = servicoRepository.findAllByAtivo(1);
        for(Servico s : servicos) {
            items.add(s.getDescricao() +" [R$"+ String.format("%.2f", s.getPreco().floatValue()/100).replace('.', ',')+"]");
        }
        tabelaServicos.setItems(items);
    }

    private Servico atualizarSelecao() {
        String descricao = tabelaServicos.getSelectionModel().getSelectedItem().split("\\[")[0];
        Optional<Servico> servico = servicoRepository.findByDescricao(descricao);
        Servico servicoObjeto;
        if (servico.isPresent()) {
            servicoObjeto = servico.get();
            return servicoObjeto;
        }else
            return null;
    }

    private Integer getInputPreco(TextField textField) {
        String valor = textField.getText();
        valor = valor.replace(",", "");
        return Integer.parseInt(valor);
    }
}
