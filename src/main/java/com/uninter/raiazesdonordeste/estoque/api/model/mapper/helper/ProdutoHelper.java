package com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper;

import com.uninter.raiazesdonordeste.cardapio.api.app.ProdutoFacade;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.ProdutoResumoDto;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoHelper {
    @Autowired
    ProdutoFacade produtoFacade;

    @Named("produtoToResumoDto")
    public ProdutoResumoDto toResumoDto(Long produtoId) {
        var produto = produtoFacade.findById(produtoId);
        return new ProdutoResumoDto(produto.id(), produto.nome());
    }
}
