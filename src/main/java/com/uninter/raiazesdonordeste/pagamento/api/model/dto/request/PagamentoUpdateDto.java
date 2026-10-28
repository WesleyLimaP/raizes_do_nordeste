package com.uninter.raiazesdonordeste.pagamento.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PagamentoUpdateDto(
        @NotNull Long pedidoId,
        @NotBlank String status,
        @NotBlank String getwayExterno,
        @NotBlank String referencialExterna,
        @NotNull Date dataHoraAtualizacao
) {
}
