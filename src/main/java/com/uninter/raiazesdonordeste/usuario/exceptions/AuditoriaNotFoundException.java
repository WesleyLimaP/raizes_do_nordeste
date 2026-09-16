package com.uninter.raiazesdonordeste.usuario.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class AuditoriaNotFoundException extends EntityNotFoundException {
    public AuditoriaNotFoundException(String message) {
        super(message);
    }
}
