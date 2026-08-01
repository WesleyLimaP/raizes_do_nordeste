package com.uninter.raiazesdonordeste.unidade.api.app;

import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;

import java.util.Optional;

public interface UnidadeFacade {
    Optional<Unidade> findById(Long id);
}
