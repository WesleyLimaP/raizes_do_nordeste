package com.uninter.raiazesdonordeste.pedido.domain.listener;

import com.uninter.raiazesdonordeste.pedido.domain.events.*;
import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;
import com.uninter.raiazesdonordeste.pedido.domain.repository.PedidoRepository;
import com.uninter.raiazesdonordeste.pedido.exceptions.PedidoNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PedidoStatusListener {
    private final PedidoRepository pedidoRepository;
    public PedidoStatusListener(PedidoRepository pedidoRepository) { this.pedidoRepository = pedidoRepository; }

    @EventListener @Transactional
    public void aoAguardarPagamento(PedidoAguardandoPagamentoEvent event) { alterar(event.pedidoId(), StatusPedido.AGUARDANDO_PAGAMENTO); }
    @EventListener @Transactional
    public void aoConfirmar(PedidoConfirmadoEvent event) { alterar(event.pedidoId(), StatusPedido.CONFIRMADO); }
    @EventListener @Transactional
    public void aoIniciarPreparo(PedidoEmPreparoEvent event) { alterar(event.pedidoId(), StatusPedido.EM_PREPARO); }
    @EventListener @Transactional
    public void aoFicarPronto(PedidoProntoEvent event) { alterar(event.pedidoId(), StatusPedido.PRONTO); }
    @EventListener @Transactional
    public void aoEntregar(PedidoEntregueEvent event) { alterar(event.pedidoId(), StatusPedido.ENTREGUE); }
    @EventListener @Transactional
    public void aoCancelar(PedidoCanceladoEvent event) { alterar(event.pedidoId(), StatusPedido.CANCELADO); }

    private void alterar(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido nao encontrado com id: " + pedidoId));
        pedido.alterarStatus(novoStatus);
        pedidoRepository.save(pedido);
    }
}
