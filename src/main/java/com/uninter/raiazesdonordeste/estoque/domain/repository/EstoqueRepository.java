package com.uninter.raiazesdonordeste.estoque.domain.repository;

import com.uninter.raiazesdonordeste.estoque.domain.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByUnidadeIdAndProdutoId(Long unidadeId, Long produtoId);

    List<Estoque> findByUnidadeId(Long unidadeId);
}
