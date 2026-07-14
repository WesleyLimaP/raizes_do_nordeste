package com.uninter.raiazesdonordeste.pedido.domain.model;

import com.uninter.raiazesdonordeste.cliente.domain.model.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tb_pedidos")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    @Enumerated
    private StatusPedido status;
    @JoinColumn(name = "cliente_id")
    private Long clienteId;
    @JoinColumn(name = "unidade_id")
    private Long unidadeId;
    @Enumerated
    private Canal canal;
}
