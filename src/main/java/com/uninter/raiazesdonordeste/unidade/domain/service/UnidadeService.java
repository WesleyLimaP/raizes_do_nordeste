package com.uninter.raiazesdonordeste.unidade.domain.service;

import com.uninter.raiazesdonordeste.cardapio.api.app.CardapioFacade;
import com.uninter.raiazesdonordeste.cardapio.exceptions.CardapioNotFoundException;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.*;
import com.uninter.raiazesdonordeste.unidade.api.model.mapper.UnidadeApiMapper;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import com.uninter.raiazesdonordeste.unidade.domain.repository.UnidadeRepository;
import com.uninter.raiazesdonordeste.unidade.exceptions.UnidadeNotFoundException;
import com.uninter.raiazesdonordeste.usuario.api.app.UsuarioFacade;
import com.uninter.raiazesdonordeste.usuario.api.app.dto.share.UsuarioUnidadeDto;
import com.uninter.raiazesdonordeste.usuario.exceptions.UsuarioNotFoundException;
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
    public List<CardapioUnidadeResumoDto> findCardapiosDaUnidade(Long unidadeId) {
        return cardapioFacade.findCardapiosDaUnidade(unidadeId);
    }

    @Transactional(readOnly = true)
    public CardapioUnidadeMaxDto findCardapioDaUnidade(Long unidadeId, Long cardapioId) {
        return cardapioFacade.findCardapioDaUnidade(unidadeId, cardapioId)
                .orElseThrow(() -> new CardapioNotFoundException
                        ("Cardapio nao encontrado com id: " + cardapioId));

    }

    @Transactional(readOnly = true)
    public List<UsuarioUnidadeDto> findUsuariosDaUnidade(Long unidadeId) {
        return usuarioFacade.findUsuariosDaUnidade(unidadeId);
    }

    @Transactional(readOnly = true)
    public UsuarioUnidadeDto findUsuarioDaUnidade(Long unidadeId, Long usuarioId) {
        return usuarioFacade.findUsuarioDaUnidade(unidadeId, usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException
                        ("Usuario nao encontrado com id: " + usuarioId));

    }

}
