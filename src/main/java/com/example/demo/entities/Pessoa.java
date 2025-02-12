package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String email;

    private String telefone;

    @OneToOne
    private Cargo cargo;

    @ManyToMany(mappedBy = "pessoas")
    private List<Servico> servicos;

    @OneToMany
    private List<Notificacao> notificacoes;

    @OneToMany
    private List<Atendimento> atendimentos;

}
