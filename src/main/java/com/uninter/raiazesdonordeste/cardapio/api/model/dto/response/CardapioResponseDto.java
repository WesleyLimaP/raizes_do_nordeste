package com.uninter.raiazesdonordeste.cardapio.api.dto.response;

import java.time.LocalDate;

public record CardapioResponseDto ( Long id,
         LocalDate vigenciaInicio,
         LocalDate vigenciaFim,
        //to do criar dto com nome e id de unidade
         Long unidadeId,
         List<CardapioItemDto> items

)
{
}
