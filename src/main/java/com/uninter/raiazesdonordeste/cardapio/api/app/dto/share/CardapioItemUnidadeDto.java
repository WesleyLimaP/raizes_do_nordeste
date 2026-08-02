package com.uninter.raiazesdonordeste.cardapio.api.app.dto.share;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.ProdutoDto;

public record CardapioItemUnidadeDto(
        Long id,
        Double precoLocal,
        boolean disponivel,
        ProdutoUnidadeDto produto){

}
