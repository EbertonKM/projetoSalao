package com.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity @Getter @Setter @ToString
@DiscriminatorValue("3") // Supondo que 3 seja Gerente
public class Gerente extends Pessoa {
}
