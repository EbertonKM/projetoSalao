package com.example.demo.controllers;

import com.example.demo.entities.Pessoa;
import org.springframework.stereotype.Controller;

@Controller
public abstract class ControleLogin {
    protected Pessoa usuarioLogado;

    public void definirLogin(Pessoa usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Pessoa getLogin() {
        return this.usuarioLogado;
    }

    public void logout() {
        this.usuarioLogado = null;
    }
}
