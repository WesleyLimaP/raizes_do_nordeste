package com.uninter.raiazesdonordeste.lgpd.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ConsentimentoLgpdUpdateDto(
        @NotNull Long clienteId,
        @NotBlank String tipoConsentimento,
        @NotNull Boolean revogado,
        @NotNull LocalDate dataConsentimento
) {
}
