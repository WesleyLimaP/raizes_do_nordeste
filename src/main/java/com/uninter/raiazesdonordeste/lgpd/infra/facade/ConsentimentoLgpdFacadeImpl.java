package com.uninter.raiazesdonordeste.lgpd.infra.facade;

import com.uninter.raiazesdonordeste.lgpd.api.app.ConsentimentoLgpdFacade;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;
import com.uninter.raiazesdonordeste.lgpd.domain.service.ConsentimentoLgpdService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsentimentoLgpdFacadeImpl implements ConsentimentoLgpdFacade {
    private final ConsentimentoLgpdService consentimentoLgpdService;

    public ConsentimentoLgpdFacadeImpl(ConsentimentoLgpdService consentimentoLgpdService) {
        this.consentimentoLgpdService = consentimentoLgpdService;
    }

    @Override
    public List<ConsentimentoLgpdResumoDto> findConsentimentosDoCliente(Long clienteId) {
        return consentimentoLgpdService.findByClienteId(clienteId);
    }
}
