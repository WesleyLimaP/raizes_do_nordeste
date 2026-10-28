package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response;

import java.util.Date;

public record MovimentoPontosMaxDto(
        Long id,
        Long contaFidelidadeId,
        String tipo,
        Date dataHora,
        Long quantidade,
        String origem
) {
}
