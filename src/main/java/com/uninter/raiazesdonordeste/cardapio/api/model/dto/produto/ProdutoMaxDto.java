package com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto;

public record ProdutoMaxDto(
        Long id,
        String nome,
        String descricao,
        Double precoBase,
        String categoria
) {
}
