package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "cargo")
public class Cargo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
}
