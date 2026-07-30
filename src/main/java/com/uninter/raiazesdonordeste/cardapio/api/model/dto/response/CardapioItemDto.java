package com.uninter.raiazesdonordeste.cardapio.api.dto.response;

public record CardapioItemDto(
        Long id,
        ProdutoDto produtoDto,
        Long quantidade){
}
