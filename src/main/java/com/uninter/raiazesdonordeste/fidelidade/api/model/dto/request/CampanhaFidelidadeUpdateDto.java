package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampanhaFidelidadeUpdateDto(
        @NotBlank String nome,
        @NotBlank String criterioSegmentacao,
        @NotNull Double desconto
) {
}
