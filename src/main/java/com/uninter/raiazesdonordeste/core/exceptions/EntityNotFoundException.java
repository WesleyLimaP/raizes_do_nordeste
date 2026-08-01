package com.uninter.raiazesdonordeste.core.exceptions;

public abstract class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
