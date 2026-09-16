package com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria;

import com.uninter.raiazesdonordeste.usuario.api.app.dto.share.UsuarioUnidadeDto;

import java.util.Date;

public record AuditoriaMaxDto(
        Long id,
        String acao,
        String entidadeAfetada,
        Date dataHora,
        String detalhes,
        UsuarioUnidadeDto usuario
) {
}
