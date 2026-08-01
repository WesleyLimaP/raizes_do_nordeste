package com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper;

import com.uninter.raiazesdonordeste.cardapio.api.app.ProdutoFacade;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.ProdutoMinDto;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoHelper {
    @Autowired
    ProdutoFacade produtoFacade;

    @Named("produtoToMinDto")
    public ProdutoMinDto toMinDto(Long produtoId) {
        var produto = produtoFacade.findById(produtoId);
        return new ProdutoMinDto(produto.getId(), produto.getNome());
    }
}
