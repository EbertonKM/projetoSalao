package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "atendimento")
public class Atendimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private LocalDateTime horario;
    private Integer valorPago;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "pessoa_profissional_id")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "pessoa_cliente_id")
    private Cliente cliente;
}
