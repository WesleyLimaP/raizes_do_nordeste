package com.uninter.raiazesdonordeste.estoque.api.model.dto.response;

public record EstoqueDto(Long id, Long quantidadeAtual, Long quantidadeMinima, UnidadeMinDto unidade, ProdutoMinDto produto) {
}
