package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CardapioItemDto(
        Long id,
        ProdutoDto produto){
}
