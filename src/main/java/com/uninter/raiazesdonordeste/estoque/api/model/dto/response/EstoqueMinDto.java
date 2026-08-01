package com.uninter.raiazesdonordeste.estoque.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EstoqueMinDto(Long id, Long quantidadeAtual, Long quantidadeMinima, Long unidadeId,Long produtoId) {
}
