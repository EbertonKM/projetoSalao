package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaInicial {

    AbridorJanela abridorJanela;

    public TelaInicial(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage) {
        abridorJanela.alterarJanela("/views/tela-inicial-view.fxml", "Realizar login", stage);
    }
}
