package com.uninter.raiazesdonordeste.cardapio.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id
    private Long id;
    private String nome;
    private String descricao;
    private Double precoBase;
    private String categoria;
    private Boolean disponibilidadeSazonal;
}
