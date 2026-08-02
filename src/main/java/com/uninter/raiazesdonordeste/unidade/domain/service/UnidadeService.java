package com.uninter.raiazesdonordeste.unidade.domain.service;

import com.uninter.raiazesdonordeste.cardapio.api.app.CardapioFacade;
import com.uninter.raiazesdonordeste.cardapio.api.app.dto.share.CardapioItemUnidadeDto;
import com.uninter.raiazesdonordeste.cardapio.exceptions.CardapioNotFoundException;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.*;
import com.uninter.raiazesdonordeste.unidade.api.model.mapper.UnidadeApiMapper;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import com.uninter.raiazesdonordeste.unidade.domain.repository.UnidadeRepository;
import com.uninter.raiazesdonordeste.unidade.exceptions.UnidadeNotFoundException;
import com.uninter.raiazesdonordeste.usuario.api.app.UsuarioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnidadeService {
    @Autowired
    private UnidadeRepository unidadeRepository;
    @Autowired
    private UnidadeApiMapper unidadeMapper;
    @Autowired
    private CardapioFacade cardapioFacade;
    @Autowired
    private UsuarioFacade usuarioFacade;

    @Transactional(readOnly = true)
    public List<UnidadeResumoDto> findAll() {
        var unidades = unidadeRepository.findAll();
        return unidadeMapper.toCollectionModel(unidades);
    }

    @Transactional(readOnly = true)
    public UnidadeMaxDto findById(Long id) {
        var unidade = getUnidadeOrElseThrow(id);
        return unidadeMapper.toModel(unidade);
    }

    @Transactional
    public UnidadeMaxDto save(UnidadePostDto unidadePostDto) {
        var unidade = unidadeMapper.toEntity(unidadePostDto);
        unidade = unidadeRepository.save(unidade);
        return unidadeMapper.toModel(unidade);
    }

    @Transactional
    public UnidadeMaxDto update(Long id, UnidadeUpdateDto unidadeUpdateDto) {
        var unidade = getUnidadeOrElseThrow(id);
        unidadeMapper.update(unidade, unidadeUpdateDto);
        unidade = unidadeRepository.save(unidade);
        return unidadeMapper.toModel(unidade);
    }

    @Transactional
    public void delete(Long id) {
        getUnidadeOrElseThrow(id);
        unidadeRepository.deleteById(id);
    }


    private Unidade getUnidadeOrElseThrow(Long id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new UnidadeNotFoundException("Unidade nao encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CardapioUnidadeResumoDto> findCardapios(Long unidadeId) {
        var dto = cardapioFacade.findAllByCardapioId(unidadeId);
        return dto.stream().map(cardapio -> new CardapioUnidadeResumoDto(
                cardapio.getId(),
                cardapio.getVigenciaInicio(),
                cardapio.getVigenciaFim()
        )).toList();
    }
    @Transactional(readOnly = true)
    public CardapioUnidadeMaxDto findCardapiosById(Long unidadeId, Long cardapioId) {
        var entity = cardapioFacade.findByIdAndUnidadeId(unidadeId, cardapioId)
                .orElseThrow(() -> new CardapioNotFoundException
                        ("Cardapio nao encontrado com id: " + cardapioId));
        return new CardapioUnidadeMaxDto(
                entity.getId(),
                entity.getVigenciaInicio(),
                entity.getVigenciaFim(),
                cardapioFacade.toModelItemCardapio(entity.getItems())
        );

    }


}
