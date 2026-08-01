package com.uninter.raiazesdonordeste.cardapio.api.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.raiazesdonordeste.core.exceptions.InvalidDateRangeException;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public record CardapioUpdateDto (
        LocalDate vigenciaInicio,
        LocalDate vigenciaFim
){
    public CardapioUpdateDto(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                             @FutureOrPresent
                             LocalDate vigenciaInicio, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                             @FutureOrPresent
                             LocalDate vigenciaFim) {
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFim = vigenciaFim;
    }
}
