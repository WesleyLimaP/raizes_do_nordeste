package com.uninter.raiazesdonordeste.unidade.api.model.dto;

import java.time.LocalDate;

public record CardapioUnidadeResumoDto(Long id,
         LocalDate vigenciaInicio,
         LocalDate vigenciaFim) {
}
