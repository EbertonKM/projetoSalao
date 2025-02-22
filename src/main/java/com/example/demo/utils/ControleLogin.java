package com.example.demo.utils;

import com.example.demo.entities.Pessoa;

public final class ControleLogin { //Criando uma classe estática
    private static Pessoa usuarioLogado = null;

    private ControleLogin() {} //Construtor privado para impedir instâncias

    public static void definirLogin(Pessoa novologin) {
        if(usuarioLogado == null) {
            usuarioLogado = novologin;
        }else
            throw new RuntimeException("Já existe um usuário logado");
    }

    public static Pessoa getLogin() {
        return usuarioLogado;
    }

    public static void logout() {
        usuarioLogado = null;
    }
}
