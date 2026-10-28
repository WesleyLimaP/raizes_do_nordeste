package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record ContaFidelidadePostDto(
        @NotNull Long pontosAcumulados,
        @NotNull Long clienteId
) {
}
