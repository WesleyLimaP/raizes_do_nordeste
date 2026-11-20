package com.uninter.raiazesdonordeste.fidelidade.infra.facade;

import com.uninter.raiazesdonordeste.fidelidade.api.app.ContaFidelidadeFacade;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.service.ContaFidelidadeService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContaFidelidadeFacadeImpl implements ContaFidelidadeFacade {
    private final ContaFidelidadeService contaFidelidadeService;

    public ContaFidelidadeFacadeImpl(ContaFidelidadeService contaFidelidadeService) {
        this.contaFidelidadeService = contaFidelidadeService;
    }

    @Override
    public Optional<ContaFidelidadeMaxDto> findByClienteId(Long clienteId) {
        return contaFidelidadeService.findByClienteId(clienteId);
    }
}
