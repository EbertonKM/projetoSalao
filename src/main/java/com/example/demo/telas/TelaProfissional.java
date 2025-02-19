package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaProfissional {

    AbridorJanela abridorJanela;

    public TelaProfissional(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-profissional-view.fxml", "Painel do Profissional", 700, 500);
    }

}
