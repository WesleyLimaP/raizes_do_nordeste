package com.uninter.raiazesdonordeste.lgpd.api.app;

import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;

import java.util.List;

public interface ConsentimentoLgpdFacade {
    List<ConsentimentoLgpdResumoDto> findConsentimentosDoCliente(Long clienteId);
}
