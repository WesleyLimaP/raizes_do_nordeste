package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampanhaFidelidadePostDto(
        @NotBlank String nome,
        @NotBlank String criterioSegmentacao,
        @NotNull Double desconto
) {
}
