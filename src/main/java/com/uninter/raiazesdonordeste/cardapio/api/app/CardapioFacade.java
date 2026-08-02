package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.api.app.dto.share.CardapioItemUnidadeDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemResponseDto;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;

import java.util.List;
import java.util.Optional;

public interface CardapioFacade {
    List<Cardapio> findAllByCardapioId(Long id);
    Optional<Cardapio> findByIdAndUnidadeId(Long unidadeId, Long cardapioId);
    List<CardapioItemUnidadeDto> toModelItemCardapio(List<CardapioItem> cardapioItem);
}
