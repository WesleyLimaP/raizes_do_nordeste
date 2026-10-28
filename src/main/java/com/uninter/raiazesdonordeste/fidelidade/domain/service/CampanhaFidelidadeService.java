package com.uninter.raiazesdonordeste.fidelidade.domain.service;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.CampanhaFidelidadePostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.CampanhaFidelidadeUpdateDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.mapper.CampanhaFidelidadeMapper;
import com.uninter.raiazesdonordeste.fidelidade.domain.model.CampanhaFidelidade;
import com.uninter.raiazesdonordeste.fidelidade.domain.repository.CampanhaFidelidadeRepository;
import com.uninter.raiazesdonordeste.fidelidade.exceptions.CampanhaFidelidadeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampanhaFidelidadeService {
    @Autowired
    private CampanhaFidelidadeRepository campanhaFidelidadeRepository;
    @Autowired
    private CampanhaFidelidadeMapper campanhaFidelidadeMapper;

    @Transactional(readOnly = true)
    public List<CampanhaFidelidadeResumoDto> findAll() {
        return campanhaFidelidadeMapper.toCollectionModel(campanhaFidelidadeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CampanhaFidelidadeMaxDto findById(Long id) {
        return campanhaFidelidadeMapper.toModel(getCampanhaFidelidadeOrElseThrow(id));
    }

    @Transactional
    public CampanhaFidelidadeMaxDto save(CampanhaFidelidadePostDto request) {
        CampanhaFidelidade campanhaFidelidade = campanhaFidelidadeMapper.toEntity(request);
        campanhaFidelidade = campanhaFidelidadeRepository.save(campanhaFidelidade);
        return campanhaFidelidadeMapper.toModel(campanhaFidelidade);
    }

    @Transactional
    public CampanhaFidelidadeMaxDto update(Long id, CampanhaFidelidadeUpdateDto request) {
        CampanhaFidelidade campanhaFidelidade = getCampanhaFidelidadeOrElseThrow(id);
        campanhaFidelidade.setNome(request.nome());
        campanhaFidelidade.setCriterioSegmentacao(request.criterioSegmentacao());
        campanhaFidelidade.setDesconto(request.desconto());
        return campanhaFidelidadeMapper.toModel(campanhaFidelidade);
    }

    @Transactional
    public void delete(Long id) {
        getCampanhaFidelidadeOrElseThrow(id);
        campanhaFidelidadeRepository.deleteById(id);
    }

    private CampanhaFidelidade getCampanhaFidelidadeOrElseThrow(Long campanhaFidelidadeId) {
        return campanhaFidelidadeRepository.findById(campanhaFidelidadeId)
                .orElseThrow(() -> new CampanhaFidelidadeNotFoundException("CampanhaFidelidade nao encontrada com id: " + campanhaFidelidadeId));
    }
}
