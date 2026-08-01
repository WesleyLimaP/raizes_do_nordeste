package com.uninter.raiazesdonordeste.estoque.domain.service;

import com.uninter.raiazesdonordeste.estoque.api.model.dto.EstoqueMovimentacaoDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueMinDto;
import com.uninter.raiazesdonordeste.estoque.api.model.mapper.EstoqueMapper;
import com.uninter.raiazesdonordeste.estoque.domain.model.Estoque;
import com.uninter.raiazesdonordeste.estoque.domain.repository.EstoqueRepository;
import com.uninter.raiazesdonordeste.estoque.exceptions.EstoqueNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private EstoqueMapper estoqueMapper;


    @Transactional(readOnly = true)
    public List<EstoqueMinDto> findAll() {
        var estoque = estoqueRepository.findAll();
        return estoqueMapper.toCollectionModel(estoque);
    }

    @Transactional(readOnly = true)
    public EstoqueDto findById(Long id) {
        var estoque = findByIdOrElseThrow(id);
        return estoqueMapper.toModel(estoque);
    }

    @Transactional(readOnly = true)
    public EstoqueDto findById(Long unidadeId, Long produtoId) {
        var estoque = findByUnidadeIdAndProdutoIdOrElseThrow(unidadeId, produtoId);
        return estoqueMapper.toModel(estoque);
    }

    @Transactional(readOnly = true)
    private @NonNull Estoque findByIdOrElseThrow(Long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new EstoqueNotFoundException("estoque com id: " + id + " nao encontrado"));
    }

    @Transactional(readOnly = true)
    private @NonNull Estoque findByUnidadeIdAndProdutoIdOrElseThrow(Long unidadeId, Long produtoId) {
        return estoqueRepository.findByUnidadeIdAndProdutoId(unidadeId, produtoId)
                .orElseThrow(() -> new EstoqueNotFoundException("estoque nao encontrado para unidade "
                        + unidadeId + " e produto " + produtoId));
    }

    @Transactional
    public EstoqueDto updateQuantidadeAtualEntrada(Long unidadeId, Long produtoId, EstoqueMovimentacaoDto request) {
        var estoque = findByUnidadeIdAndProdutoIdOrElseThrow(unidadeId, produtoId);
        return adicionarQuantidade(estoque, request.quantidade());
    }

    @Transactional
    public EstoqueDto updateQuantidadeAtualEntrada(Long id, EstoqueMovimentacaoDto request) {
        var estoque = findByIdOrElseThrow(id);
        return adicionarQuantidade(estoque, request.quantidade());
    }

    @Transactional
    public EstoqueDto updateQuantidadeAtualSaida(Long unidadeId, Long produtoId, EstoqueMovimentacaoDto request) {
        var estoque = findByUnidadeIdAndProdutoIdOrElseThrow(unidadeId, produtoId);
        return removerQuantidade(estoque, request.quantidade());
    }

    @Transactional
    public EstoqueDto updateQuantidadeAtualSaida(Long id, EstoqueMovimentacaoDto request) {
        var estoque = findByIdOrElseThrow(id);
        return removerQuantidade(estoque, request.quantidade());
    }

    private EstoqueDto adicionarQuantidade(Estoque estoque, Integer quantidade) {
        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        return estoqueMapper.toModel(estoque);
    }

    private EstoqueDto removerQuantidade(Estoque estoque, Integer quantidade) {
        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - quantidade);
        return estoqueMapper.toModel(estoque);
    }

    @Transactional(readOnly = true)
    public List<EstoqueMinDto> findAllByUnidade(Long unidadeId) {
        var estoque = estoqueRepository.findByUnidadeId(unidadeId);
        return estoqueMapper.toCollectionModel(estoque);
    }

    @Transactional
    public void criarEstoque(Long unidadeId, Long produtoId) {
        var entity = estoqueRepository.findByUnidadeIdAndProdutoId(unidadeId, produtoId);
        if (entity.isEmpty()) {
            estoqueRepository.save(new Estoque(0, 0,unidadeId, produtoId));
        }
    }
}
