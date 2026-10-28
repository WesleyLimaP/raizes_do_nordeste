package com.uninter.raiazesdonordeste.usuario.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioPostDto(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank String senhahash,
        @NotNull Long unidadeId
) {
}
