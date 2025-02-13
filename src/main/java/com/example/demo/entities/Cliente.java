package com.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@DiscriminatorValue("1") // ID 1 sendo Cliente
public class Cliente extends Pessoa {
    @OneToMany(mappedBy = "cliente")
    private List<Atendimento> atendimentos;
}