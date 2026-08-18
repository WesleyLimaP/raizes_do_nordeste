package com.uninter.raiazesdonordeste.cardapio.api.app.dto.share;

public record CardapioItemUnidadeDto(
        Long id,
        Double precoLocal,
        boolean disponivel,
        ProdutoUnidadeDto produto){

}
