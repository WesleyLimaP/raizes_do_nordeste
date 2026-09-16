package com.uninter.raiazesdonordeste.pedido.api.model.dto.request;

import com.uninter.raiazesdonordeste.pedido.domain.model.Canal;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PedidoUpdateDto(
        @NotNull Canal canal,
        @NotNull LocalDate dataHora,
        @NotNull Long clienteId,
        @NotNull Long unidadeId
) {
}
