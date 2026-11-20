package com.uninter.raiazesdonordeste.cliente.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class ClienteNotFoundException extends EntityNotFoundException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
