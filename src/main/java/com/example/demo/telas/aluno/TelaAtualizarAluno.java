package com.example.demo.telas.aluno;

import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class TelaAtualizarAluno {

    AbridorJanela abridorJanela;

    public TelaAtualizarAluno(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/aluno/tela-atualizar-aluno-view.fxml", "Atualizar Alunos", 300, 300);
    }

    public FXMLLoader getXMLLoader(){
        return abridorJanela.getFxmlLoader();
    }

}
