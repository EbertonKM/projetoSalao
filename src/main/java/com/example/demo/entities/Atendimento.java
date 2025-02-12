package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;

    private LocalDateTime horario;

    @Column(name = "valor_pago")
    private int valorPago;

    @OneToOne
    private Servico servico;

    @OneToOne
    private Pessoa profissional;

    @OneToOne
    private Pessoa cliente;
}
