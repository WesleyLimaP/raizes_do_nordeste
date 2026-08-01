package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;

public interface ProdutoFacade {
    Produto findById(Long id);
}
