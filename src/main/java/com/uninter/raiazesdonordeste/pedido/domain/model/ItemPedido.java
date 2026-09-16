package com.uninter.raiazesdonordeste.pedido.domain.model;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;
    private Double precoUnitario;
    private Long quantidade;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
