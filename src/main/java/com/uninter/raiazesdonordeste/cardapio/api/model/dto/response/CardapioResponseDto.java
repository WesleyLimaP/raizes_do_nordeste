package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

import java.time.LocalDate;

public record CardapioResponseDto (
        Long id,
         LocalDate vigenciaInicio,
         LocalDate vigenciaFim,
         UnidadeMinDto unidade

)
{
}
