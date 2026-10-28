package com.uninter.raiazesdonordeste.usuario.domain.service;

import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.PapelPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.PapelUpdateDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.PapelMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.PapelResumoDto;
import com.uninter.raiazesdonordeste.usuario.api.model.mapper.PapelMapper;
import com.uninter.raiazesdonordeste.usuario.domain.model.Papel;
import com.uninter.raiazesdonordeste.usuario.domain.repository.PapelRepository;
import com.uninter.raiazesdonordeste.usuario.exceptions.PapelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PapelService {
    @Autowired
    private PapelRepository papelRepository;
    @Autowired
    private PapelMapper papelMapper;

    @Transactional(readOnly = true)
    public List<PapelResumoDto> findAll() {
        return papelMapper.toCollectionModel(papelRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PapelMaxDto findById(Long id) {
        return papelMapper.toModel(getPapelOrElseThrow(id));
    }

    @Transactional
    public PapelMaxDto save(PapelPostDto request) {
        Papel papel = papelMapper.toEntity(request);
        papel = papelRepository.save(papel);
        return papelMapper.toModel(papel);
    }

    @Transactional
    public PapelMaxDto update(Long id, PapelUpdateDto request) {
        Papel papel = getPapelOrElseThrow(id);
        papel.setDescricao(request.descricao());
        return papelMapper.toModel(papel);
    }

    @Transactional
    public void delete(Long id) {
        getPapelOrElseThrow(id);
        papelRepository.deleteById(id);
    }

    private Papel getPapelOrElseThrow(Long papelId) {
        return papelRepository.findById(papelId)
                .orElseThrow(() -> new PapelNotFoundException("Papel nao encontrado com id: " + papelId));
    }
}
