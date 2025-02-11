package com.example.demo.controllers;

import com.example.demo.telas.aluno.TelaAlunos;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class TelaInicialController {

    private TelaAlunos telaAlunos;

    public TelaInicialController(TelaAlunos telaAlunos) {
        this.telaAlunos = telaAlunos;
    }

    @FXML
    protected void onAlunosButtonClick() {
        telaAlunos.abrir();
    }

}
