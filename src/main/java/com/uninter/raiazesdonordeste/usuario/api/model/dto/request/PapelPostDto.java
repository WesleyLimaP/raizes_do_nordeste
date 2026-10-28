package com.uninter.raiazesdonordeste.usuario.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PapelPostDto(
        @NotBlank String descricao
) {
}
