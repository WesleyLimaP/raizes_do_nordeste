package com.uninter.raiazesdonordeste.unidade.api.model.dto;

public record UnidadeUpdateDto(
        String nome,
        String endereco,
        String regiao,
        String tipoCozinha
) {
}
