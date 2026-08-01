package com.uninter.raiazesdonordeste.cardapio.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class CardapioNotFoundException extends EntityNotFoundException {
    public CardapioNotFoundException(String message) {
        super(message);
    }
}
