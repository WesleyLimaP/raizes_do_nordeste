package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

public record CardapioItemResponseDto(
        Long id,
        Double precoLocal,
        boolean disponivel,
        ProdutoDto produto){
}
