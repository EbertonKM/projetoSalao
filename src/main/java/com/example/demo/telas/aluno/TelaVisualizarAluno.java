package com.example.demo.telas.aluno;

import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class TelaVisualizarAluno {

    AbridorJanela abridorJanela;

    public TelaVisualizarAluno(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/aluno/tela-visualizar-aluno-view.fxml", "Visualizar Aluno", 300, 200);
    }

    public FXMLLoader getXMLLoader(){
        return abridorJanela.getFxmlLoader();
    }

}
