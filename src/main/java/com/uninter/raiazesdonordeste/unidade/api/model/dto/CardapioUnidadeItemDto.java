package com.uninter.raiazesdonordeste.unidade.api.model.dto;

public record CardapioUnidadeItemDto(
        Long id,
        String nome,
        String descricao,
        Double preco,
        String categoria
) {
}
