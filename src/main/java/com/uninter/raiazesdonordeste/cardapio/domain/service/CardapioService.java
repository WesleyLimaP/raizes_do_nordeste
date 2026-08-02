package com.uninter.raiazesdonordeste.cardapio.domain.service;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.ItemPrecoLocalDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioUpdateDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioItemPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResponseMinDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResponseDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemResponseDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.CardapioItemMapper;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.CardapioMapper;
import com.uninter.raiazesdonordeste.cardapio.domain.events.CardapioItemCriadoEvent;
import com.uninter.raiazesdonordeste.unidade.api.app.UnidadeFacade;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.CardapioRepository;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.ProdutoRepository;
import com.uninter.raiazesdonordeste.cardapio.exceptions.CardapioNotFoundException;
import com.uninter.raiazesdonordeste.cardapio.exceptions.ItemNotRelatedException;
import com.uninter.raiazesdonordeste.cardapio.exceptions.ProdutoNotRelatedException;
import com.uninter.raiazesdonordeste.core.exceptions.EntityNotRelatedException;
import com.uninter.raiazesdonordeste.core.exceptions.InvalidDateRangeException;
import com.uninter.raiazesdonordeste.unidade.exceptions.UnidadeNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;
    @Autowired
    private UnidadeFacade unidadeFacade;
    @Autowired
    private CardapioMapper cardapioMapper;
    @Autowired
    private CardapioItemMapper cardapioItemMapper;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public List<CardapioResponseMinDto> findAll() {
        var cardapios = cardapioRepository.findAll();
        return cardapioMapper.toCollectionModel(cardapios);
    }

    @Transactional(readOnly = true)
    public CardapioResponseDto findById(Long id) {
        Cardapio cardapio = getCardapioOrElseThrow(id);
        return cardapioMapper.toModel(cardapio, unidadeFacade.findById(cardapio.getUnidadeId()).get());
    }

    @Transactional
    public CardapioResponseDto save(CardapioPostDto request) {
        IsvalidRangeDate(request, null);
        Cardapio cardapio = cardapioMapper.toEntity(request);
        var unidade = unidadeFacade.findById(request.getUnidadeId()).orElseThrow(() -> new UnidadeNotFoundException("unidade de id: "+request.getUnidadeId() + " não encontrada"));
        cardapio = cardapioRepository.save(cardapio);
    return cardapioMapper.toModel(cardapio, unidade);
    }

    private static void IsvalidRangeDate(CardapioPostDto postRequest, CardapioUpdateDto updateRequest) {
        if (postRequest != null) {
            if(postRequest.getVigenciaFim().isBefore(postRequest.getVigenciaInicio())){
                throw new InvalidDateRangeException("A data de fim deve ser maior que a data de inicio");
            }
        }else{
            if(updateRequest.vigenciaFim().isBefore(updateRequest.vigenciaInicio())){
                throw new InvalidDateRangeException("A data de fim deve ser maior que a data de inicio");
            }
        }


    }

    @Transactional
    public CardapioResponseDto update(Long id, CardapioUpdateDto request) {
        Cardapio cardapio = getCardapioOrElseThrow(id);
        IsvalidRangeDate(null, request);
        cardapio.setVigenciaInicio(request.vigenciaInicio());
        cardapio.setVigenciaFim(request.vigenciaFim());
        return cardapioMapper.toModel(cardapio, unidadeFacade.findById(cardapio.getUnidadeId()).get());
    }

    @Transactional
    public void delete(Long id) {
        getCardapioOrElseThrow(id);
        cardapioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CardapioItemDto> findByItems(Long cardapioId) {
        getCardapioOrElseThrow(cardapioId);
        var itemEntity = cardapioRepository.findItemsByCardapioId(cardapioId);
        return cardapioItemMapper.toCollectionModel(itemEntity);
    }

    @Transactional
    public CardapioItemResponseDto saveItem(Long cardapioId, CardapioItemPostDto request) {
        Cardapio cardapio = getCardapioOrElseThrow(cardapioId);
        getProdutoRelatedOrElseThrow(cardapioId, request);
        CardapioItem cardapioItem = cardapioItemMapper.toEntity(request, cardapioId);
        cardapioItem = cardapioRepository.saveItem(cardapioItem);
        eventPublisher.publishEvent(new CardapioItemCriadoEvent(cardapio.getUnidadeId(), request.produtoId()));
        return cardapioItemMapper.toModel(cardapioItem);
    }

    private void getProdutoRelatedOrElseThrow(Long cardapioId, CardapioItemPostDto request) {
        if (!produtoRepository.existsById(request.produtoId())) {
            throw new ProdutoNotRelatedException("produto com id: " + request.produtoId() + " não está relacionado com o cardápio de id: " + cardapioId, cardapioId, request.produtoId());
        }
    }

    @Transactional
    public void deleteItem(Long cardapioId, Long id) {
        var cardapio = getCardapioOrElseThrow(cardapioId);
        cardapio.getItems().removeIf(item -> item.getId().equals(id));
        var item = getItemRelatedOrElseThrow(cardapioId, id);
        cardapioRepository.deleteItem(item);
    }

    @Transactional(readOnly = true)
    public CardapioItemResponseDto findItemById(Long cardapioId, Long cardapioItemId) {
        getCardapioOrElseThrow(cardapioId);
       var item = getItemRelatedOrElseThrow(cardapioId, cardapioItemId);
       return cardapioItemMapper.toModel(item);
    }

    private @NonNull CardapioItem getItemRelatedOrElseThrow(Long cardapioId, Long cardapioItemId) {
        return cardapioRepository.findItemById(cardapioItemId, cardapioId)
                .orElseThrow(() -> new ItemNotRelatedException("nenhum Item do cardápio com id: " + cardapioItemId + "encontrado em cardapio de id:" + cardapioId, cardapioId, cardapioItemId));
    }

    @Transactional
    public void disponibilizarItem(Long cardapioId, Long id) {
        getCardapioOrElseThrow(cardapioId);
        CardapioItem item = getItemRelatedOrElseThrow(cardapioId, id);
        item.setDisponivel(true);
    }

    private Cardapio getCardapioOrElseThrow(Long cardapioId) {
        if (!cardapioRepository.existsById(cardapioId)) {
            throw new CardapioNotFoundException("Cardápio não encontrado com id: " + cardapioId);
        }
        return cardapioRepository.findById(cardapioId).get();
    }

    @Transactional
    public void indisponibilizarItem(Long cardapioId, Long itemId) {
        getCardapioOrElseThrow(cardapioId);
        CardapioItem item = getItemRelatedOrElseThrow(cardapioId, itemId);
        item.setDisponivel(false);
    }
    @Transactional
    public CardapioItemResponseDto updateItem(Long cardapioId, Long id, ItemPrecoLocalDto cardapioItem) {
        var cardapio = getCardapioOrElseThrow(cardapioId);
        var items = cardapio.getItems()
                .stream()
                .filter(item -> item.getId().equals(id)).findFirst()
                .map(item -> {
                    item.setPrecoLocal(cardapioItem.precoLocal());
                    return item;
                })
                .orElseThrow(() -> new EntityNotRelatedException("nenhum Item do cardápio com id: " + id + "encontrado em cardapio de id:" +cardapioId, cardapioId, id));

        return cardapioItemMapper.toModel(items);
    }

}
