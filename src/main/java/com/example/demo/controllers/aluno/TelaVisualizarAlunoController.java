package com.example.demo.controllers.aluno;

import com.example.demo.entities.Aluno;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class TelaVisualizarAlunoController {

    @FXML
    private Label labelNome;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelIdade;

    public void setAluno(Aluno aluno){
        labelNome.setText(aluno.getNome());
        labelEmail.setText(aluno.getEmail());
        labelIdade.setText(String.valueOf(aluno.getIdade()));
    }

    @FXML
    private void onFecharButtonClick(){
        labelNome.getScene().getWindow().hide();
    }

}
