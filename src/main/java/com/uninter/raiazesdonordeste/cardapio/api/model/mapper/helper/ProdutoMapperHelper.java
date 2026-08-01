package com.uninter.raiazesdonordeste.cardapio.api.model.mapper.helper;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProdutoMapperHelper {
    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public Produto findProdutoById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }
}
