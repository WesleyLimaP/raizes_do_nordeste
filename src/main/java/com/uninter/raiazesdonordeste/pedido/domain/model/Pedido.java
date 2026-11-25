package com.uninter.raiazesdonordeste.pedido.domain.model;

import com.uninter.raiazesdonordeste.pedido.domain.events.*;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING) private Canal canal;
    @Enumerated(EnumType.STRING) private StatusPedido status;
    private LocalDate dataHora;
    private Long clienteId;
    private Long unidadeId;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();
    @Transient private ApplicationEventPublisher eventPublisher;

    public void aguardarPagamento() { publicarTransicao(StatusPedido.AGUARDANDO_PAGAMENTO, new PedidoAguardandoPagamentoEvent(id)); }
    public void confirmar() { publicarTransicao(StatusPedido.CONFIRMADO, new PedidoConfirmadoEvent(id)); }
    public void iniciarPreparo() { publicarTransicao(StatusPedido.EM_PREPARO, new PedidoEmPreparoEvent(id)); }
    public void pronto() { publicarTransicao(StatusPedido.PRONTO, new PedidoProntoEvent(id)); }
    public void cancelar() { publicarTransicao(StatusPedido.CANCELADO, new PedidoCanceladoEvent(id)); }
    public void entregar() { publicarTransicao(StatusPedido.ENTREGUE, new PedidoEntregueEvent(id)); }

    public void alterarStatus(StatusPedido novoStatus) {
        validarTransicao(novoStatus);
        this.status = novoStatus;
    }

    private void publicarTransicao(StatusPedido novoStatus, Object evento) {
        validarTransicao(novoStatus);
        if (eventPublisher == null) throw new IllegalStateException("Publicador de eventos nao configurado");
        eventPublisher.publishEvent(evento);
    }

    private void validarTransicao(StatusPedido novoStatus) {
        if (!StatusPedido.Isnext(status, novoStatus)) {
            throw new IllegalStateException("Transicao de status invalida: " + status + " -> " + novoStatus);
        }
    }
}
