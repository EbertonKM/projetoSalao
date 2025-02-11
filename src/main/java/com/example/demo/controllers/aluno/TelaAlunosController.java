package com.example.demo.controllers.aluno;

import com.example.demo.controllers.contato.TelaContatosController;
import com.example.demo.entities.Aluno;
import com.example.demo.services.AlunoService;
import com.example.demo.telas.aluno.TelaAdicionarAluno;
import com.example.demo.telas.aluno.TelaAtualizarAluno;
import com.example.demo.telas.aluno.TelaVisualizarAluno;
import com.example.demo.telas.contato.TelaContatosAluno;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

@Controller
public class TelaAlunosController {

    private final AlunoService alunoService;
    private final TelaAdicionarAluno telaAdicionarAluno;
    private final TelaAtualizarAluno telaAtualizarAluno;
    private final TelaVisualizarAluno telaVisualizarAluno;
    private final TelaContatosAluno telaContatosAluno;

    @FXML
    private TableView<Aluno> tableViewAlunos;

    @FXML
    private TableColumn<Aluno, Integer> columnId;

    @FXML
    private TableColumn<Aluno, String> columnNome;

    public TelaAlunosController(AlunoService alunoService,
                                TelaAdicionarAluno telaAdicionarAluno,
                                TelaAtualizarAluno telaAtualizarAluno, TelaVisualizarAluno telaVisualizarAluno, TelaContatosAluno telaContatosAluno) {
        this.alunoService = alunoService;
        this.telaAdicionarAluno = telaAdicionarAluno;
        this.telaAtualizarAluno = telaAtualizarAluno;
        this.telaVisualizarAluno = telaVisualizarAluno;
        this.telaContatosAluno = telaContatosAluno;
    }

    @FXML
    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableViewAlunos.getItems().addAll(alunoService.buscarTodos());
    }

    @FXML
    protected void onAdicionarAlunoButtonClick(){
        telaAdicionarAluno.abrir();
        TelaAdicionarAlunoController adicionarAlunoController = telaAdicionarAluno.getXMLLoader().getController();
        adicionarAlunoController.setAlunoController(this);
    }

    @FXML
    protected void onRemoverAlunoButtonClick(){
        if(tableViewAlunos.getSelectionModel().getSelectedItem() != null){
            Aluno aluno = tableViewAlunos.getSelectionModel().getSelectedItem();
            alunoService.excluir(aluno);
            tableViewAlunos.getItems().remove(aluno);
        }
    }

    @FXML
    protected void onEditarAlunoButtonClick(){
        if(tableViewAlunos.getSelectionModel().getSelectedItem() != null){
            Aluno aluno = tableViewAlunos.getSelectionModel().getSelectedItem();
            telaAtualizarAluno.abrir();
            TelaAtualizarAlunoController atualizarAlunoController = telaAtualizarAluno.getXMLLoader().getController();
            atualizarAlunoController.setAlunoController(this);
            atualizarAlunoController.setAluno(aluno);
        }
    }

    @FXML
    protected void onAbrirDetalhesButtonClick(){
        if(tableViewAlunos.getSelectionModel().getSelectedItem() != null){
            Aluno aluno = tableViewAlunos.getSelectionModel().getSelectedItem();
            telaVisualizarAluno.abrir();
            TelaVisualizarAlunoController visualizarAlunoController = telaVisualizarAluno.getXMLLoader().getController();
            visualizarAlunoController.setAluno(aluno);
        }
    }

    @FXML
    protected void onAbrirContatosButtonClick(){
        if(tableViewAlunos.getSelectionModel().getSelectedItem() != null){
            Aluno aluno = tableViewAlunos.getSelectionModel().getSelectedItem();
            telaContatosAluno.abrir();
            TelaContatosController contatosAlunoController = telaContatosAluno.getXMLLoader().getController();
            contatosAlunoController.carregaContatosDoAluno(aluno);
        }
    }

    public void adicionarAluno(Aluno aluno) {
        alunoService.salvar(aluno);
        tableViewAlunos.getItems().add(aluno);
    }

    public void atualizarAluno(Aluno aluno) {
        alunoService.salvar(aluno);
        tableViewAlunos.getItems().set(tableViewAlunos.getSelectionModel().getSelectedIndex(), aluno);
    }

}
