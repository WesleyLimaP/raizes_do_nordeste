package com.uninter.raiazesdonordeste.fidelidade.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_movimento_pontos")
public class MovimentoPontos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contaFidelidadeId;
    private String tipo;
    private Date dataHora;
    private Long quantidade;
    private String origem;
}
