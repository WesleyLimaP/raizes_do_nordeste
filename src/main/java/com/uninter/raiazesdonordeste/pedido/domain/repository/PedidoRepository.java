package com.uninter.raiazesdonordeste.pedido.domain.repository;

import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
