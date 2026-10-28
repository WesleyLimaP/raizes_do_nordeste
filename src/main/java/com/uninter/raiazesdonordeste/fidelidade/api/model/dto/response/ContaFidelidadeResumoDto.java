package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response;

public record ContaFidelidadeResumoDto(
        Long id,
        Long pontosAcumulados,
        Long clienteId
) {
}
