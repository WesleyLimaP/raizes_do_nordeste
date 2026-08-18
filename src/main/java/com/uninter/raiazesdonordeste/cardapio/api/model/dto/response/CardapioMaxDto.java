package com.uninter.raiazesdonordeste.cardapio.api.model.dto.response;

import java.time.LocalDate;

public record CardapioMaxDto (
        Long id,
         LocalDate vigenciaInicio,
         LocalDate vigenciaFim,
         UnidadeResumoDto unidade

)
{
}
