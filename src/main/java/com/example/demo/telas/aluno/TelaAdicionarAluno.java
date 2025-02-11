package com.example.demo.telas.aluno;

import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class TelaAdicionarAluno {

    AbridorJanela abridorJanela;

    public TelaAdicionarAluno(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/aluno/tela-adicionar-aluno-view.fxml", "Adicionar Alunos", 300, 300);
    }

    public FXMLLoader getXMLLoader(){
        return abridorJanela.getFxmlLoader();
    }

}
