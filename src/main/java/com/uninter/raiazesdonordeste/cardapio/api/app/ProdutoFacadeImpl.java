package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.ProdutoRepository;
import com.uninter.raiazesdonordeste.cardapio.domain.service.ProdutoService;
import com.uninter.raiazesdonordeste.cardapio.exceptions.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoFacadeImpl implements ProdutoFacade {

    @Autowired
    private ProdutoRepository produtoService;

    @Override
    public Produto findById(Long id) {
        return produtoService.findById(id).orElseThrow(() -> new ProdutoNotFoundException("produto com id: " + id + " nao encontrado"));
    }
}
