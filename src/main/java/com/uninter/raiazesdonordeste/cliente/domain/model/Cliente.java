package com.uninter.raiazesdonordeste.cliente.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    @Transient
    private java.util.List<Long> pedidosIds;
    @Transient
    private Long contaFidelidadeId;
    @Transient
    private java.util.List<Long> consentimentosLgpdIds;
    @Transient
    private java.util.List<Long> campanhasFidelidadeIds;
}
