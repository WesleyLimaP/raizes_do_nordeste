package com.uninter.raiazesdonordeste.pedido.api.model.dto.response;

import com.uninter.raiazesdonordeste.pedido.domain.model.Canal;
import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;

import java.time.LocalDate;

public record PedidoResumoDto(
        Long id,
        Canal canal,
        StatusPedido status,
        LocalDate dataHora,
        Long clienteId,
        Long unidadeId
) {
}
