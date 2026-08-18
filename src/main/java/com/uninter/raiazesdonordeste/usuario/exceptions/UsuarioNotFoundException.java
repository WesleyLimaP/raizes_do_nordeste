package com.uninter.raiazesdonordeste.usuario.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class UsuarioNotFoundException extends EntityNotFoundException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
