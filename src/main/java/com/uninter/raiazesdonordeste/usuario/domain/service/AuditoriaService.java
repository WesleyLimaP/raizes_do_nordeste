package com.uninter.raiazesdonordeste.usuario.domain.service;

import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaResumoDto;
import com.uninter.raiazesdonordeste.usuario.api.model.mapper.AuditoriaMapper;
import com.uninter.raiazesdonordeste.usuario.domain.model.Auditoria;
import com.uninter.raiazesdonordeste.usuario.domain.repository.AuditoriaRepository;
import com.uninter.raiazesdonordeste.usuario.exceptions.AuditoriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditoriaService {
    @Autowired
    private AuditoriaRepository auditoriaRepository;
    @Autowired
    private AuditoriaMapper auditoriaMapper;

    @Transactional(readOnly = true)
    public List<AuditoriaResumoDto> findAll() {
        var auditorias = auditoriaRepository.findAll();
        return auditoriaMapper.toCollectionModel(auditorias);
    }

    @Transactional(readOnly = true)
    public AuditoriaMaxDto findById(Long id) {
        var auditoria = getAuditoriaOrElseThrow(id);
        return auditoriaMapper.toModel(auditoria);
    }

    private Auditoria getAuditoriaOrElseThrow(Long id) {
        return auditoriaRepository.findById(id)
                .orElseThrow(() -> new AuditoriaNotFoundException("Auditoria nao encontrada com id: " + id));
    }
}
