package com.uninter.raiazesdonordeste.unidade.infra.facade;

import com.uninter.raiazesdonordeste.unidade.api.app.UnidadeFacade;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import com.uninter.raiazesdonordeste.unidade.domain.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnidadeFacadeImpl implements UnidadeFacade {
    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    public Optional<Unidade> findById(Long id) {
        return unidadeRepository.findById(id);
    }
}
