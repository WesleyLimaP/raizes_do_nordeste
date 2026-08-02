package com.uninter.raiazesdonordeste.cardapio.api.app.dto.share;

public record  ProdutoUnidadeDto(
        Long id,
        String nome,
        String descricao,
        Double precoBase,
        String categoria
) {
}
