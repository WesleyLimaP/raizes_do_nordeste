package com.uninter.raiazesdonordeste.usuario.api.model.dto.response;

public record UsuarioResumoDto(
        Long id,
        String nome,
        String login,
        String senhahash,
        Long unidadeId
) {
}
