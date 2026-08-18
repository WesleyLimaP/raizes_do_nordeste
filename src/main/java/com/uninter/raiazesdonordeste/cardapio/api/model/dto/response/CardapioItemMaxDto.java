package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

public record CardapioItemMaxDto(
        Long id,
        Double precoLocal,
        boolean disponivel,
        ProdutoCardapioResumoDto produto){
}
