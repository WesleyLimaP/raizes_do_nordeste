package com.uninter.raiazesdonordeste.fidelidade.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
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
