package com.uninter.raiazesdonordeste.pedido.api.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoPostDto(
        @NotNull @Positive Double precoUnitario,
        @NotNull @Positive Long quantidade,
        @NotNull Long produtoId
) {
}
