package com.uninter.raiazesdonordeste.unidade.api.app;

import com.uninter.raiazesdonordeste.unidade.api.model.dto.UnidadeResumoDto;

import java.util.Optional;

public interface UnidadeFacade {
    Optional<UnidadeResumoDto> findById(Long id);
}
