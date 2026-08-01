package com.uninter.raiazesdonordeste.estoque.api.model.dto.request;

public record EstoquePostDto(Long quantidadeAtual, Long quantidadeMinima, Long unidadeId, Long produtoId) {
}
