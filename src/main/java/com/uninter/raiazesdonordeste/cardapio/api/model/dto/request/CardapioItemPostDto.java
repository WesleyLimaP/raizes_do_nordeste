package com.uninter.raiazesdonordeste.cardapio.api.model.dto.request;

public record CardapioItemPostDto(
         Double precoLocal,
         Boolean disponivel,
         Long produtoId
) {
}
