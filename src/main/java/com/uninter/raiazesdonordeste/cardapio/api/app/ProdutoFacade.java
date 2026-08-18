package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoResumoDto;

public interface ProdutoFacade {
    ProdutoResumoDto findById(Long id);
}
