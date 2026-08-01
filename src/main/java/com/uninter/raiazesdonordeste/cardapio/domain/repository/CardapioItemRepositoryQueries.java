package com.uninter.raiazesdonordeste.cardapio.domain.repository;

import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;

public interface CardapioItemRepositoryQueries {

    CardapioItem saveItem(CardapioItem cardapioItem);
    void deleteItem(CardapioItem item);


}
