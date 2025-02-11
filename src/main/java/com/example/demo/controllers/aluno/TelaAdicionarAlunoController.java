package com.example.demo.controllers.aluno;

import com.example.demo.entities.Aluno;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class TelaAdicionarAlunoController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdade;

    private TelaAlunosController telaAlunosController;

    public void setAlunoController(TelaAlunosController telaAlunosController) {
        this.telaAlunosController = telaAlunosController;
    }

    @FXML
    protected void onSalvarButtonClick(){
        try {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            int idade = Integer.parseInt(txtIdade.getText());

            Aluno aluno = new Aluno();
            aluno.setNome(nome);
            aluno.setEmail(email);
            aluno.setIdade(idade);

            telaAlunosController.adicionarAluno(aluno);

            fechaJanela();
        } catch (Exception e) {
            System.out.println("Você preencheu algum campo incorretamente!");
        }
    }

    private void fechaJanela() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }

}
