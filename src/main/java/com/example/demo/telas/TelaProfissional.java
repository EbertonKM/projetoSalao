package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaProfissional {

    AbridorJanela abridorJanela;

    public TelaProfissional(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage){
        abridorJanela.alterarJanela("/views/tela-profissional-view.fxml", "Painel do profissional", stage);
    }

}
