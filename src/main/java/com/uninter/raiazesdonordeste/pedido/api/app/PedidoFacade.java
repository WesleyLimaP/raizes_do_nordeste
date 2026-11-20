package com.uninter.raiazesdonordeste.pedido.api.app;

import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;

import java.util.List;

public interface PedidoFacade {
    List<PedidoResumoDto> findPedidosDoCliente(Long clienteId);
}
