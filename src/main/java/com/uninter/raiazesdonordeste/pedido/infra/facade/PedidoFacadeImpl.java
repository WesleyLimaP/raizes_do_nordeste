package com.uninter.raiazesdonordeste.pedido.infra.facade;

import com.uninter.raiazesdonordeste.pedido.api.app.PedidoFacade;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.domain.service.PedidoService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoFacadeImpl implements PedidoFacade {
    private final PedidoService pedidoService;

    public PedidoFacadeImpl(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    public List<PedidoResumoDto> findPedidosDoCliente(Long clienteId) {
        return pedidoService.findAll(null, clienteId);
    }
}
