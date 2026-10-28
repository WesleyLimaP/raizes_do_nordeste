package com.uninter.raiazesdonordeste.lgpd.api.model.dto.response;

import java.time.LocalDate;

public record ConsentimentoLgpdMaxDto(
        Long id,
        Long clienteId,
        String tipoConsentimento,
        Boolean revogado,
        LocalDate dataConsentimento
) {
}
