package com.uninter.raiazesdonordeste.cardapio.domain.repository;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long>, CardapioItemRepositoryQueries {
    @Query("SELECT i FROM CardapioItem i JOIN FETCH i.produto WHERE i.cardapio.id = :cardapioId")
    List<CardapioItem> findItemsByCardapioId(Long cardapioId);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_cardapio_item WHERE cardapio_id = :cardapioId AND id = :itemId")
    Optional<CardapioItem> findItemById(Long itemId, Long cardapioId);

    Optional<Cardapio> findByUnidadeIdAndId(Long unidadeId, Long cardapioId);

    List<Cardapio> findAllByUnidadeId(Long id);
}
