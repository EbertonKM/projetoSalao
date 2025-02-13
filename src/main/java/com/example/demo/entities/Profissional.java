package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@DiscriminatorValue("2") // ID 2 sendo Profissional
public class Profissional extends Pessoa {
    @ManyToMany
    @JoinTable(name = "pessoa_has_servico",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private List<Servico> servicos;

    @OneToMany(mappedBy = "profissional")
    private List<Atendimento> atendimentos;
}