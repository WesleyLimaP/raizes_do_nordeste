package com.uninter.raiazesdonordeste.estoque.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidadeAtual;
    private Integer quantidadeMinima;
    private Long produtoId;
    private Long unidadeId;

    public Estoque(int quantidadeAtual, int quantidadeMinima, Long unidadeId, Long produtoId) {
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeMinima = quantidadeMinima;
        this.unidadeId = unidadeId;
        this.produtoId = produtoId;
    }
}
