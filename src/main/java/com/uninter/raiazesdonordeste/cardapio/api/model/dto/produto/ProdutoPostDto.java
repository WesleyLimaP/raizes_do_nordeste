package com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto;

public record ProdutoPostDto(
        String nome,
        String descricao,
        Double precoBase,
        String categoria
) {
}
