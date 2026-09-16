package com.uninter.raiazesdonordeste.pedido.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class PedidoNotFoundException extends EntityNotFoundException {
    public PedidoNotFoundException(String message) {
        super(message);
    }
}
