package com.uninter.raiazesdonordeste.pedido.domain.repository;

import com.uninter.raiazesdonordeste.pedido.domain.model.ItemPedido;

public interface PedidoItemRepositoryQueries {
    ItemPedido saveItem(ItemPedido itemPedido);

    void deleteItem(ItemPedido itemPedido);
}
