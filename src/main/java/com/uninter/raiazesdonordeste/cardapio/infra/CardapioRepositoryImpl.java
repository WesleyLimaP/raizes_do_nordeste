package com.uninter.raiazesdonordeste.cardapio.infra;

import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.CardapioItemRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardapioRepositoryImpl implements CardapioItemRepositoryQueries {
    @Autowired
    private EntityManager entityManager;

    @Override
    public CardapioItem saveItem(CardapioItem cardapioItem) {
        cardapioItem = entityManager.merge(cardapioItem);
        entityManager.flush();
        return cardapioItem;
    }

    @Override
    public void deleteItem(CardapioItem item) {
        entityManager.remove(item);
        entityManager.flush();

    }
}
