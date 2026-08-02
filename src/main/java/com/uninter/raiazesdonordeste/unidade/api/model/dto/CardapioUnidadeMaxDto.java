package com.uninter.raiazesdonordeste.unidade.api.model.dto;

import com.uninter.raiazesdonordeste.cardapio.api.app.dto.share.CardapioItemUnidadeDto;

import java.time.LocalDate;
import java.util.List;

public record CardapioUnidadeMaxDto(Long id, LocalDate vigenciaInicio, LocalDate vigenciaFim, List<CardapioItemUnidadeDto> items) {
}
