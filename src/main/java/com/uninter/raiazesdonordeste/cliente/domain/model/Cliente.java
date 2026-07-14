package com.uninter.raiazesdonordeste.cliente.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
}
