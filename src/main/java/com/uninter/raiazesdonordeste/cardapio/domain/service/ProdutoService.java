package com.uninter.raiazesdonordeste.cardapio.domain.service;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoMaxDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoUpdateDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.ProdutoMapper;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.ProdutoRepository;
import com.uninter.raiazesdonordeste.cardapio.exceptions.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ProdutoMapper produtoMapper;

    @Transactional(readOnly = true)
    public List<ProdutoResumoDto> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtoMapper.toCollectionModel(produtos);
    }

    @Transactional
    public ProdutoMaxDto save(ProdutoPostDto produtoPostDto) {
        Produto produto = produtoMapper.toEntity(produtoPostDto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toModel(produto);
    }

    @Transactional
    public void delete(Long id) {
        getProdutoOrElseThrow(id);
        produtoRepository.deleteById(id);
    }

    private Produto getProdutoOrElseThrow(Long id) {
       return produtoRepository.findById(id).orElseThrow(()
                        -> new ProdutoNotFoundException("Produto não encontrado com id: " + id));
    }

    @Transactional(readOnly = true)
    public ProdutoMaxDto findById(Long id) {
        Produto produto = getProdutoOrElseThrow(id);
        return produtoMapper.toModel(produto);
    }
    @Transactional
    public ProdutoMaxDto update(long produtoId, ProdutoUpdateDto produtoUpdateDto){
        Produto produto = getProdutoOrElseThrow(produtoId);
        produtoMapper.update(produto, produtoUpdateDto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toModel(produto);
    }




}
