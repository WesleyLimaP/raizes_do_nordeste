package com.uninter.raiazesdonordeste.cardapio.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class CardapioItemNotFoundException extends EntityNotFoundException {
    public CardapioItemNotFoundException(String message) {
        super(message);
    }
}
