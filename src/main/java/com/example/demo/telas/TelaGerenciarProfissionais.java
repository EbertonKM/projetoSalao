package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TelaGerenciarProfissionais {

    AbridorJanela abridorJanela;

    public TelaGerenciarProfissionais(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage) {
        abridorJanela.alterarJanela("/views/tela-gerenciar-profissionais-view.fxml", "Gerenciar profissionais", stage);
    }
}
