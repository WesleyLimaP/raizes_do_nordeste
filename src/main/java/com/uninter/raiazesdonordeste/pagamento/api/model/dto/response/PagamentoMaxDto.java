package com.uninter.raiazesdonordeste.pagamento.api.model.dto.response;

import java.util.Date;

public record PagamentoMaxDto(
        Long id,
        Long pedidoId,
        String status,
        String getwayExterno,
        String referencialExterna,
        Date dataHoraAtualizacao
) {
}
