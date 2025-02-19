package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaGerente {

    AbridorJanela abridorJanela;

    public TelaGerente(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-gerente-view.fxml", "Painel do Gerente", 700, 500);
    }
}
