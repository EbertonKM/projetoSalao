package com.example.demo.utils;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Gerente;
import com.example.demo.entities.Profissional;
import com.example.demo.telas.TelaCliente;
import com.example.demo.telas.TelaGerente;
import com.example.demo.telas.TelaProfissional;

public final class NavegacaoUtils {
    private NavegacaoUtils() {}

    public static void voltarPainel(TelaCliente telaCliente,
                               TelaProfissional telaProfissional,
                               TelaGerente telaGerente) {
        switch (ControleLogin.getLogin()) {
            case Cliente cliente -> {
                telaCliente.abrir(null);
            }
            case Profissional profissional -> {
                telaProfissional.abrir(null);
            }
            case Gerente gerente -> {
                telaGerente.abrir(null);
            }
            default -> {throw new RuntimeException("Nenhum login encontrado");}
        }
    }
}
