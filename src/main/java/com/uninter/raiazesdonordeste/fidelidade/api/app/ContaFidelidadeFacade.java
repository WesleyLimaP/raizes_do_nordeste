package com.uninter.raiazesdonordeste.fidelidade.api.app;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;

import java.util.Optional;

public interface ContaFidelidadeFacade {
    Optional<ContaFidelidadeMaxDto> findByClienteId(Long clienteId);
}
