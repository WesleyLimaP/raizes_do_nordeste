package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.ProdutoMapper;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.ProdutoRepository;
import com.uninter.raiazesdonordeste.cardapio.exceptions.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoFacadeImpl implements ProdutoFacade {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoMapper produtoMapper;

    @Override
    public ProdutoResumoDto findById(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("produto com id: " + id + " nao encontrado"));
        return produtoMapper.toResumoModel(produto);
    }
}
