package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

public record ProdutoDto(
        Long id,
        String nome,
        Double precoBase,
        String categoria

) {
}
