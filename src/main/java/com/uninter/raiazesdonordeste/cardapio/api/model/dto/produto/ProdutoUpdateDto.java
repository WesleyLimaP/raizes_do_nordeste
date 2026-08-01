package com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto;

public record ProdutoUpdateDto(
        String nome,
        String descricao,
        Double precoBase
) {
}
