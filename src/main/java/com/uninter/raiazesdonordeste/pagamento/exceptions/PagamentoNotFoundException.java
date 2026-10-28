package com.uninter.raiazesdonordeste.pagamento.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;

public class PagamentoNotFoundException extends EntityNotFoundException {
    public PagamentoNotFoundException(String message) {
        super(message);
    }
}
