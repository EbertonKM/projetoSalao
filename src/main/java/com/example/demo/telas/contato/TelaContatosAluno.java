package com.example.demo.telas.contato;

import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class TelaContatosAluno {

    AbridorJanela abridorJanela;

    public TelaContatosAluno(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/contato/tela-contatos-view.fxml", "Contatos do Aluno", 600, 500);
    }

    public FXMLLoader getXMLLoader(){
        return abridorJanela.getFxmlLoader();
    }

}
