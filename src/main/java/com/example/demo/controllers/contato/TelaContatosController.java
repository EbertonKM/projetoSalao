package com.example.demo.controllers.contato;

import com.example.demo.entities.Aluno;
import com.example.demo.entities.Contato;
import com.example.demo.services.AlunoService;
import com.example.demo.services.ContatoService;
import com.example.demo.telas.contato.TelaAdicionarContato;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TelaContatosController {

    private final AlunoService alunoService;
    private ContatoService contatoService;
    private TelaAdicionarContato telaAdicionarContato;

    @FXML
    private TableView<Contato> tableViewContatos;

    @FXML
    private TableColumn<Contato, Integer> columnId;

    @FXML
    private TableColumn<Contato, String> columnDescricao;

    @FXML
    private TableColumn<Contato, String> columnTipo;

    private Aluno aluno;

    public TelaContatosController(ContatoService contatoService, AlunoService alunoService, TelaAdicionarContato telaAdicionarContato) {
        this.contatoService = contatoService;
        this.alunoService = alunoService;
        this.telaAdicionarContato = telaAdicionarContato;
    }

    @FXML
    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    @FXML
    protected void onRemoverContatoButtonClick(){
        if(tableViewContatos.getSelectionModel().getSelectedItem() != null){
            Contato contato = tableViewContatos.getSelectionModel().getSelectedItem();
            contatoService.excluir(contato);
            tableViewContatos.getItems().remove(contato);
        }
    }

    @FXML
    protected void onAdicionarContatoButtonClick(){
        telaAdicionarContato.abrir();
        TelaAdicionarContatoController adicionarContatoController = telaAdicionarContato.getXMLLoader().getController();
        adicionarContatoController.setContatoController(this);
    }

    public void carregaContatosDoAluno(Aluno aluno){
        this.aluno = aluno;
        List<Contato> contatos = contatoService.buscarPorAlunoId(aluno.getId());
        tableViewContatos.getItems().addAll(contatos);
    }

    public void adicionarContato(Contato contato) {
        contato.setAluno(aluno);
        contatoService.salvar(contato);
        tableViewContatos.getItems().add(contato);
    }
}
