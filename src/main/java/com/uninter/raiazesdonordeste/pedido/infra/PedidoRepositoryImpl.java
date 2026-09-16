package com.uninter.raiazesdonordeste.pedido.infra;

import com.uninter.raiazesdonordeste.pedido.domain.model.ItemPedido;
import com.uninter.raiazesdonordeste.pedido.domain.repository.PedidoItemRepositoryQueries;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepositoryImpl implements PedidoItemRepositoryQueries {
    private final EntityManager entityManager;

    public PedidoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ItemPedido saveItem(ItemPedido itemPedido) {
        itemPedido = entityManager.merge(itemPedido);
        entityManager.flush();
        return itemPedido;
    }

    @Override
    public void deleteItem(ItemPedido itemPedido) {
        entityManager.remove(itemPedido);
        entityManager.flush();
    }
}
