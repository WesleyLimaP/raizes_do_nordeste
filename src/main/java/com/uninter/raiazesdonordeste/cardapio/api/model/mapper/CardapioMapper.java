package com.uninter.raiazesdonordeste.cardapio.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioMaxDto;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.UnidadeResumoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardapioMapper {


    @Mapping(target = "id", source = "cardapio.id")
    @Mapping(target = "unidade", source = "unidade")
    CardapioMaxDto toModel(Cardapio cardapio, UnidadeResumoDto unidade);
    Cardapio toEntity(CardapioPostDto request);


    @Mapping(target = "unidadeId", source = "cardapio.unidadeId")
    List<CardapioResumoDto> toCollectionModel(List<Cardapio> cardapios);
}

