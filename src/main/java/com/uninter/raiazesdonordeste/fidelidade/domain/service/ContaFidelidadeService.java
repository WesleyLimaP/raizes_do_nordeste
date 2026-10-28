package com.uninter.raiazesdonordeste.fidelidade.domain.service;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.ContaFidelidadePostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.ContaFidelidadeUpdateDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.mapper.ContaFidelidadeMapper;
import com.uninter.raiazesdonordeste.fidelidade.domain.model.ContaFidelidade;
import com.uninter.raiazesdonordeste.fidelidade.domain.repository.ContaFidelidadeRepository;
import com.uninter.raiazesdonordeste.fidelidade.exceptions.ContaFidelidadeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaFidelidadeService {
    @Autowired
    private ContaFidelidadeRepository contaFidelidadeRepository;
    @Autowired
    private ContaFidelidadeMapper contaFidelidadeMapper;

    @Transactional(readOnly = true)
    public List<ContaFidelidadeResumoDto> findAll() {
        return contaFidelidadeMapper.toCollectionModel(contaFidelidadeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ContaFidelidadeMaxDto findById(Long id) {
        return contaFidelidadeMapper.toModel(getContaFidelidadeOrElseThrow(id));
    }

    @Transactional
    public ContaFidelidadeMaxDto save(ContaFidelidadePostDto request) {
        ContaFidelidade contaFidelidade = contaFidelidadeMapper.toEntity(request);
        contaFidelidade = contaFidelidadeRepository.save(contaFidelidade);
        return contaFidelidadeMapper.toModel(contaFidelidade);
    }

    @Transactional
    public ContaFidelidadeMaxDto update(Long id, ContaFidelidadeUpdateDto request) {
        ContaFidelidade contaFidelidade = getContaFidelidadeOrElseThrow(id);
        contaFidelidade.setPontosAcumulados(request.pontosAcumulados());
        contaFidelidade.setClienteId(request.clienteId());
        return contaFidelidadeMapper.toModel(contaFidelidade);
    }

    @Transactional
    public void delete(Long id) {
        getContaFidelidadeOrElseThrow(id);
        contaFidelidadeRepository.deleteById(id);
    }

    private ContaFidelidade getContaFidelidadeOrElseThrow(Long contaFidelidadeId) {
        return contaFidelidadeRepository.findById(contaFidelidadeId)
                .orElseThrow(() -> new ContaFidelidadeNotFoundException("ContaFidelidade nao encontrada com id: " + contaFidelidadeId));
    }
}
