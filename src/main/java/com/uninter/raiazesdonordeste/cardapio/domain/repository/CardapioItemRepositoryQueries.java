package com.uninter.raiazesdonordeste.cardapio.domain.repository;

import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;

import java.util.List;

public interface CardapioItemQuery {

    CardapioItem saveItem(CardapioItem cardapioItem);
    void deleteItem(Long id);


}
