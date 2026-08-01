package com.uninter.raiazesdonordeste.cardapio.domain.repository;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>


{
}
