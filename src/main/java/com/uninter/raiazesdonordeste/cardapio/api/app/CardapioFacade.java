package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.unidade.api.model.dto.CardapioUnidadeMaxDto;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.CardapioUnidadeResumoDto;

import java.util.List;
import java.util.Optional;

public interface CardapioFacade {
    List<CardapioUnidadeResumoDto> findCardapiosDaUnidade(Long unidadeId);
    Optional<CardapioUnidadeMaxDto> findCardapioDaUnidade(Long unidadeId, Long cardapioId);
}
