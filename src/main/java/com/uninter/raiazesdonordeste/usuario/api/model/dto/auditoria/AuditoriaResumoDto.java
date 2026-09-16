package com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria;

import java.util.Date;

public record AuditoriaResumoDto(
        Long id,
        String acao,
        String entidadeAfetada,
        Date dataHora,
        Long usuarioId
) {
}
