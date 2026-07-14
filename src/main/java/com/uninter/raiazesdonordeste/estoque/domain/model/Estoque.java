package com.uninter.raiazesdonordeste.estoque.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Estoque {
    @Id
    private Long id;
    private Integer quantidadeAtual;
    private Integer quantidadeMinima;
    private Long produtoId;
    private Long unidadeId;
}
