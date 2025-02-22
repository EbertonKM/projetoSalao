package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaGerente {

    AbridorJanela abridorJanela;

    public TelaGerente(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage){
        abridorJanela.alterarJanela("/views/tela-gerente-view.fxml", "Painel do gerente", stage);
    }
}
