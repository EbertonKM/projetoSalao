package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaGerenciarServicos {

    AbridorJanela abridorJanela;

    public TelaGerenciarServicos(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage) {
        abridorJanela.alterarJanela("/views/tela-gerenciar-servicos-view.fxml", "Gerenciar serviços", stage);
    }
}
