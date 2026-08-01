package com.uninter.raiazesdonordeste.cardapio.api.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.raiazesdonordeste.core.exceptions.InvalidDateRangeException;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;


import java.time.LocalDate;

@Data
public class CardapioPostDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate vigenciaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate vigenciaFim;
    private final Long unidadeId;

    public CardapioPostDto(Long unidadeId, LocalDate vigenciaInicio, LocalDate vigenciaFim) {
        this.unidadeId = unidadeId;
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFim = vigenciaFim;
    }
}

