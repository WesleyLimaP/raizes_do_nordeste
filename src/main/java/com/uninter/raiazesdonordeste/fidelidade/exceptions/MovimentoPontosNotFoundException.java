package com.uninter.raiazesdonordeste.fidelidade.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class MovimentoPontosNotFoundException extends EntityNotFoundException {
    public MovimentoPontosNotFoundException(String message) {
        super(message);
    }
}
