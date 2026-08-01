package com.uninter.raiazesdonordeste.unidade.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class UnidadeNotFoundException extends EntityNotFoundException {
    public UnidadeNotFoundException(String message) {
        super(message);
    }
}
