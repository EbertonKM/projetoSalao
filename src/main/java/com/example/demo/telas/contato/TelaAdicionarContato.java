package com.example.demo.telas.contato;

import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class TelaAdicionarContato {

    AbridorJanela abridorJanela;

    public TelaAdicionarContato(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/contato/tela-adicionar-contato-view.fxml", "Adicionar Contato", 300, 300);
    }

    public FXMLLoader getXMLLoader(){
        return abridorJanela.getFxmlLoader();
    }

}
