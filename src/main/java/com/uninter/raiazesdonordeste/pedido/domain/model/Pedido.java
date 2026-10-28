package com.uninter.raiazesdonordeste.pedido.domain.model;

import com.uninter.raiazesdonordeste.pedido.domain.events.PedidoAguardandoPagamentoEvent;
import com.uninter.raiazesdonordeste.pedido.domain.events.PedidoCanceladoEvent;
import com.uninter.raiazesdonordeste.pedido.domain.events.PedidoEntregueEvent;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Canal canal;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private LocalDate dataHora;
    private Long clienteId;
    private Long unidadeId;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();

    @Transient
    private ApplicationEventPublisher eventPublisher;

    //todo verificação se o retorno da logica no enum de status pedido nao é falsa
    public void aguardarPagamento() {
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        if (eventPublisher != null) {
            eventPublisher.publishEvent(new PedidoAguardandoPagamentoEvent(this.id));
        }
    }

    public void cancelar() {
        this.status = StatusPedido.CANCELADO;
        if (eventPublisher != null) {
            eventPublisher.publishEvent(new PedidoCanceladoEvent(this.id));
        }
    }

    public void entregar() {
        this.status = StatusPedido.ENTREGUE;
        if (eventPublisher != null) {
            eventPublisher.publishEvent(new PedidoEntregueEvent(this.id));
        }
    }

}
