package com.uninter.raiazesdonordeste.pedido.api.model.dto.request;

import com.uninter.raiazesdonordeste.pedido.domain.model.Canal;
import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PedidoPostDto(
        @NotNull Canal canal,
        @NotNull LocalDate dataHora,
        @NotNull Long clienteId,
        @NotNull Long unidadeId
) {
}
