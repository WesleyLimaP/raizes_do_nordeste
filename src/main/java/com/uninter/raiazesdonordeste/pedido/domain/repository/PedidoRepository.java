package com.uninter.raiazesdonordeste.pedido.domain.repository;

import com.uninter.raiazesdonordeste.pedido.domain.model.ItemPedido;
import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoItemRepositoryQueries {
    @Query("SELECT p FROM Pedido p WHERE " +
           "(:unidadeId IS NULL OR p.unidadeId = :unidadeId) AND " +
           "(:clienteId IS NULL OR p.clienteId = :clienteId)")
    List<Pedido> findByUnidadeIdAndClienteId(Long unidadeId, Long clienteId);

    @Query("SELECT i FROM ItemPedido i JOIN FETCH i.produto WHERE i.pedido.id = :pedidoId")
    List<ItemPedido> findItemsByPedidoId(Long pedidoId);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_item_pedido WHERE pedido_id = :pedidoId AND id = :itemId")
    Optional<ItemPedido> findItemById(Long itemId, Long pedidoId);
}
