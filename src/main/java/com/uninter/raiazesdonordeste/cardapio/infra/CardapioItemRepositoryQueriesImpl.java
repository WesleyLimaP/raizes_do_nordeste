package com.uninter.raiazesdonordeste.cardapio.infra;

import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.CardapioItemQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardapioItemQueryImpl implements CardapioItemQuery {

    @Override
    public CardapioItem saveItem(CardapioItem cardapioItem) {
        return null;
    }

    @Override
    public void deleteItem(Long id) {

    }
}
