package com.uninter.raiazesdonordeste.cardapio.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotRelatedException;

public class ItemNotRelatedException extends EntityNotRelatedException {
    public ItemNotRelatedException(String message, Long id, Long relatedId) {
        super(message, id, relatedId);
    }
}
