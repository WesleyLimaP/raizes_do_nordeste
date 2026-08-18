package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

public record ProdutoCardapioResumoDto(
        Long id,
        String nome,
        Double precoBase,
        String categoria

) {
}
