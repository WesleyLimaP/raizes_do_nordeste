package com.uninter.raiazesdonordeste.cardapio.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResponseMinDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResponseDto;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardapioMapper {


    @Mapping(target = "id", source = "cardapio.id")
    @Mapping(target = "unidade", source = "unidade")
    CardapioResponseDto toModel(Cardapio cardapio, Unidade unidade);
    Cardapio toEntity(CardapioPostDto request);


    @Mapping(target = "unidadeId", source = "cardapio.unidadeId")
    List<CardapioResponseMinDto> toCollectionModel(List<Cardapio> cardapios);
}

