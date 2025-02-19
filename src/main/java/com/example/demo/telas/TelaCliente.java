package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaCliente {

    AbridorJanela abridorJanela;

    public TelaCliente(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-cliente-view.fxml", "Painel do Cliente", 700, 500);
    }
}
