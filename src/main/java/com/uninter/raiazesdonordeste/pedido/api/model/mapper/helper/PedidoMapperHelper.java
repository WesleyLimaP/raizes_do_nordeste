package com.uninter.raiazesdonordeste.pedido.api.model.mapper.helper;

import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import com.uninter.raiazesdonordeste.pedido.domain.repository.PedidoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PedidoMapperHelper {
    private final PedidoRepository pedidoRepository;

    public PedidoMapperHelper(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional(readOnly = true)
    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
