package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response;

import java.util.Date;

public record MovimentoPontosResumoDto(
        Long id,
        Long contaFidelidadeId,
        String tipo,
        Date dataHora,
        Long quantidade,
        String origem
) {
}
