package com.example.demo.telas;

import com.example.demo.utils.AbridorJanela;
import org.springframework.stereotype.Component;

@Component
public class TelaCadastrar {

    AbridorJanela abridorJanela;

    public TelaCadastrar(AbridorJanela abridorJanela) {
        this.abridorJanela = abridorJanela;
    }

    public void abrir(){
        abridorJanela.abrirNovaJanela("/views/tela-cadastrar-view.fxml", "Cadastre-se", 700, 500);
    }

}