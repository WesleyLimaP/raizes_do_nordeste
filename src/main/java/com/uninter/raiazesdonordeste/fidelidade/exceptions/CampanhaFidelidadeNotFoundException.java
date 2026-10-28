package com.uninter.raiazesdonordeste.fidelidade.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class CampanhaFidelidadeNotFoundException extends EntityNotFoundException {
    public CampanhaFidelidadeNotFoundException(String message) {
        super(message);
    }
}
