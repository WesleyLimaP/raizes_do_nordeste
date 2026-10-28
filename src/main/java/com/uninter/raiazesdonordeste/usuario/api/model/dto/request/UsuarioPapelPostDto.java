package com.uninter.raiazesdonordeste.usuario.api.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record UsuarioPapelPostDto(
        @NotNull Long usuarioId,
        @NotNull Long papelId
) {
}
