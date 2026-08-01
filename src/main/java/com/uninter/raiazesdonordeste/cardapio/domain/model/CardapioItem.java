package com.uninter.raiazesdonordeste.cardapio.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cardapio_item")
public class CardapioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double precoLocal;
    private Boolean disponivel;
    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
