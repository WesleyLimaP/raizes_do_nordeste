package com.uninter.raiazesdonordeste.lgpd.domain.service;

import com.uninter.raiazesdonordeste.lgpd.api.model.dto.request.ConsentimentoLgpdPostDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.request.ConsentimentoLgpdUpdateDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdMaxDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.mapper.ConsentimentoLgpdMapper;
import com.uninter.raiazesdonordeste.lgpd.domain.model.ConsentimentoLgpd;
import com.uninter.raiazesdonordeste.lgpd.domain.repository.ConsentimentoLgpdRepository;
import com.uninter.raiazesdonordeste.lgpd.exceptions.ConsentimentoLgpdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsentimentoLgpdService {
    @Autowired
    private ConsentimentoLgpdRepository consentimentoLgpdRepository;
    @Autowired
    private ConsentimentoLgpdMapper consentimentoLgpdMapper;

    @Transactional(readOnly = true)
    public List<ConsentimentoLgpdResumoDto> findAll() {
        return consentimentoLgpdMapper.toCollectionModel(consentimentoLgpdRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ConsentimentoLgpdMaxDto findById(Long id) {
        return consentimentoLgpdMapper.toModel(getConsentimentoLgpdOrElseThrow(id));
    }

    @Transactional
    public ConsentimentoLgpdMaxDto save(ConsentimentoLgpdPostDto request) {
        ConsentimentoLgpd consentimentoLgpd = consentimentoLgpdMapper.toEntity(request);
        consentimentoLgpd = consentimentoLgpdRepository.save(consentimentoLgpd);
        return consentimentoLgpdMapper.toModel(consentimentoLgpd);
    }

    @Transactional
    public ConsentimentoLgpdMaxDto update(Long id, ConsentimentoLgpdUpdateDto request) {
        ConsentimentoLgpd consentimentoLgpd = getConsentimentoLgpdOrElseThrow(id);
        consentimentoLgpd.setClienteId(request.clienteId());
        consentimentoLgpd.setTipoConsentimento(request.tipoConsentimento());
        consentimentoLgpd.setRevogado(request.revogado());
        consentimentoLgpd.setDataConsentimento(request.dataConsentimento());
        return consentimentoLgpdMapper.toModel(consentimentoLgpd);
    }

    @Transactional
    public void delete(Long id) {
        getConsentimentoLgpdOrElseThrow(id);
        consentimentoLgpdRepository.deleteById(id);
    }

    private ConsentimentoLgpd getConsentimentoLgpdOrElseThrow(Long consentimentoLgpdId) {
        return consentimentoLgpdRepository.findById(consentimentoLgpdId)
                .orElseThrow(() -> new ConsentimentoLgpdNotFoundException("ConsentimentoLgpd nao encontrado com id: " + consentimentoLgpdId));
    }
}
