package com.uninter.raiazesdonordeste.estoque.api.model.dto.response;

public record EstoqueMaxDto(Long id, Long quantidadeAtual, Long quantidadeMinima, UnidadeResumoDto unidade, ProdutoResumoDto produto) {
}
