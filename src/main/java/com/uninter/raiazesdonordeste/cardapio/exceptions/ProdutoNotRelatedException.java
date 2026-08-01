package com.uninter.raiazesdonordeste.cardapio.exceptions;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotRelatedException;

public class ProdutoNotRelatedException extends EntityNotRelatedException {
   public ProdutoNotRelatedException(String message, Long id, Long relatedId) {
       super(message, id, relatedId);
   }
}
