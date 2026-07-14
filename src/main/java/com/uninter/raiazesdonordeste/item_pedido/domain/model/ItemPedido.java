package com.uninter.raiazesdonordeste.item_pedido.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ItemPedido {
    @Id
    private Long id;
    private Long pedidoId;
    private Double precoUnitario;
    private Long quantidade;
    private Long produtoId;
}
