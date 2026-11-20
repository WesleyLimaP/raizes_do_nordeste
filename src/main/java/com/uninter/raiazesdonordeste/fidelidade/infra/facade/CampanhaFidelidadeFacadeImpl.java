package com.uninter.raiazesdonordeste.fidelidade.infra.facade;

import com.uninter.raiazesdonordeste.fidelidade.api.app.CampanhaFidelidadeFacade;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.service.CampanhaFidelidadeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampanhaFidelidadeFacadeImpl implements CampanhaFidelidadeFacade {
    private final CampanhaFidelidadeService campanhaFidelidadeService;

    public CampanhaFidelidadeFacadeImpl(CampanhaFidelidadeService campanhaFidelidadeService) {
        this.campanhaFidelidadeService = campanhaFidelidadeService;
    }

    @Override
    public List<CampanhaFidelidadeResumoDto> findCampanhasDoCliente(Long clienteId) {
        return campanhaFidelidadeService.findByClienteId(clienteId);
    }
}
