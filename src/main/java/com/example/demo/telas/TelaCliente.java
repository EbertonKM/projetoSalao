package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaCliente {

    AbridorJanela abridorJanela;

    public TelaCliente(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage){
        abridorJanela.alterarJanela("/views/tela-cliente-view.fxml", "Painel do cliente", stage);
    }
}
