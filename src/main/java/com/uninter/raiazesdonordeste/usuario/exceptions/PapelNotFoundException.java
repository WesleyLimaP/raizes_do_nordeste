package com.uninter.raiazesdonordeste.usuario.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class PapelNotFoundException extends EntityNotFoundException {
    public PapelNotFoundException(String message) {
        super(message);
    }
}
