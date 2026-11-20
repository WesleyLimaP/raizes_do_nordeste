package com.uninter.raiazesdonordeste.fidelidade.api.app;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;

import java.util.List;

public interface CampanhaFidelidadeFacade {
    List<CampanhaFidelidadeResumoDto> findCampanhasDoCliente(Long clienteId);
}
