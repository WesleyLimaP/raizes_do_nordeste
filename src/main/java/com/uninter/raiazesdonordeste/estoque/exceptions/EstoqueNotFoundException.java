package com.uninter.raiazesdonordeste.estoque.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class EstoqueNotFoundException extends EntityNotFoundException {
    public EstoqueNotFoundException(String s) {
        super(s);
    }
}
