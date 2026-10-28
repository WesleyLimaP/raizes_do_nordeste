package com.uninter.raiazesdonordeste.fidelidade.domain.service;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.MovimentoPontosPostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.MovimentoPontosUpdateDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.MovimentoPontosMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.MovimentoPontosResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.mapper.MovimentoPontosMapper;
import com.uninter.raiazesdonordeste.fidelidade.domain.model.MovimentoPontos;
import com.uninter.raiazesdonordeste.fidelidade.domain.repository.MovimentoPontosRepository;
import com.uninter.raiazesdonordeste.fidelidade.exceptions.MovimentoPontosNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentoPontosService {
    @Autowired
    private MovimentoPontosRepository movimentoPontosRepository;
    @Autowired
    private MovimentoPontosMapper movimentoPontosMapper;

    @Transactional(readOnly = true)
    public List<MovimentoPontosResumoDto> findAll() {
        return movimentoPontosMapper.toCollectionModel(movimentoPontosRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MovimentoPontosMaxDto findById(Long id) {
        return movimentoPontosMapper.toModel(getMovimentoPontosOrElseThrow(id));
    }

    @Transactional
    public MovimentoPontosMaxDto save(MovimentoPontosPostDto request) {
        MovimentoPontos movimentoPontos = movimentoPontosMapper.toEntity(request);
        movimentoPontos = movimentoPontosRepository.save(movimentoPontos);
        return movimentoPontosMapper.toModel(movimentoPontos);
    }

    @Transactional
    public MovimentoPontosMaxDto update(Long id, MovimentoPontosUpdateDto request) {
        MovimentoPontos movimentoPontos = getMovimentoPontosOrElseThrow(id);
        movimentoPontos.setContaFidelidadeId(request.contaFidelidadeId());
        movimentoPontos.setTipo(request.tipo());
        movimentoPontos.setDataHora(request.dataHora());
        movimentoPontos.setQuantidade(request.quantidade());
        movimentoPontos.setOrigem(request.origem());
        return movimentoPontosMapper.toModel(movimentoPontos);
    }

    @Transactional
    public void delete(Long id) {
        getMovimentoPontosOrElseThrow(id);
        movimentoPontosRepository.deleteById(id);
    }

    private MovimentoPontos getMovimentoPontosOrElseThrow(Long movimentoPontosId) {
        return movimentoPontosRepository.findById(movimentoPontosId)
                .orElseThrow(() -> new MovimentoPontosNotFoundException("MovimentoPontos nao encontrado com id: " + movimentoPontosId));
    }
}
