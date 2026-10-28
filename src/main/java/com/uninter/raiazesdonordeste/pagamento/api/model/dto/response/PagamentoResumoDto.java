package com.uninter.raiazesdonordeste.pagamento.api.model.dto.response;

import java.util.Date;

public record PagamentoResumoDto(
        Long id,
        Long pedidoId,
        String status,
        String getwayExterno,
        String referencialExterna,
        Date dataHoraAtualizacao
) {
}
