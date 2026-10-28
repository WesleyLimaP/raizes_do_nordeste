package com.uninter.raiazesdonordeste.usuario.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuditoriaPostDto(
        @NotBlank String acao,
        @NotBlank String entidadeAfetada,
        String detalhes,
        Long usuarioId
) {
}
