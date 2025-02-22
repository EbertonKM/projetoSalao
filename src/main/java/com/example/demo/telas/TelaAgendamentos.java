package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class TelaAgendamentos {

    @FXML
    private Button voltarButton;

    AbridorJanela abridorJanela;

    public TelaAgendamentos(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(Stage stage) {
        abridorJanela.alterarJanela("/views/tela-agendamentos-view.fxml", "Agendamentos", stage);
    }
}
