package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.api.app.dto.share.CardapioItemUnidadeDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemResponseDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.CardapioItemMapper;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardapioFacadeImpl implements CardapioFacade {
    @Autowired
    private CardapioRepository cardapioRepository;
    @Autowired
    private CardapioItemMapper cardapioItemMapper;

    @Override
    public List<Cardapio> findAllByCardapioId(Long id) {
        return cardapioRepository.findAllByUnidadeId(id);
    }

    @Override
    public Optional<Cardapio> findByIdAndUnidadeId(Long unidadeId, Long cardapioId) {
        return cardapioRepository.findByUnidadeIdAndId(unidadeId, cardapioId);
    }

    @Override
    public List<CardapioItemUnidadeDto> toModelItemCardapio(List<CardapioItem> cardapioItem) {
        return cardapioItemMapper.toCollectionModelItemCardapio(cardapioItem);
    }
}
