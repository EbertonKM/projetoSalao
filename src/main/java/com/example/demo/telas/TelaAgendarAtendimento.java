package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaAgendarAtendimento {

    AbridorJanela abridorJanela;

    public TelaAgendarAtendimento(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage) {
        abridorJanela.alterarJanela("/views/tela-agendar-view.fxml", "Agendar atendimento", stage);
    }
}
