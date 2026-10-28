package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MovimentoPontosPostDto(
        @NotNull Long contaFidelidadeId,
        @NotBlank String tipo,
        @NotNull Date dataHora,
        @NotNull Long quantidade,
        @NotBlank String origem
) {
}
