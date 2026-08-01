package com.uninter.raiazesdonordeste.cardapio.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class ProdutoNotFoundException extends EntityNotFoundException {
    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
