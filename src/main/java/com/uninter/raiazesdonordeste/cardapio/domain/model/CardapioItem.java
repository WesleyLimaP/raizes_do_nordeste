package com.uninter.raiazesdonordeste.cardapio.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CardapioItem {
    @Id
    private Long id;
    private Double precoLocal;
    private Boolean disponivel;
    private Long cardapioId;
    private Long produtoId;
}
