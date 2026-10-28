package com.uninter.raiazesdonordeste.fidelidade.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class ContaFidelidadeNotFoundException extends EntityNotFoundException {
    public ContaFidelidadeNotFoundException(String message) {
        super(message);
    }
}
