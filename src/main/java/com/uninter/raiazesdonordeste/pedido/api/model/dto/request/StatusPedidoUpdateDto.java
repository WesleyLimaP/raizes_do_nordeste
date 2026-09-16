package com.uninter.raiazesdonordeste.pedido.api.model.dto.request;

import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record StatusPedidoUpdateDto(
        @NotNull StatusPedido status
) {
}
