package com.uninter.raiazesdonordeste.cardapio.api.app;

import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.CardapioItemMapper;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.CardapioRepository;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.CardapioUnidadeMaxDto;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.CardapioUnidadeResumoDto;
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
    public List<CardapioUnidadeResumoDto> findCardapiosDaUnidade(Long unidadeId) {
        return cardapioRepository.findAllByUnidadeId(unidadeId)
                .stream()
                .map(cardapio -> new CardapioUnidadeResumoDto(
                        cardapio.getId(),
                        cardapio.getVigenciaInicio(),
                        cardapio.getVigenciaFim()
                ))
                .toList();
    }

    @Override
    public Optional<CardapioUnidadeMaxDto> findCardapioDaUnidade(Long unidadeId, Long cardapioId) {
        return cardapioRepository.findByUnidadeIdAndId(unidadeId, cardapioId)
                .map(cardapio -> new CardapioUnidadeMaxDto(
                        cardapio.getId(),
                        cardapio.getVigenciaInicio(),
                        cardapio.getVigenciaFim(),
                        cardapioItemMapper.toCollectionModelItemCardapio(cardapio.getItems())
                ));
    }
}
