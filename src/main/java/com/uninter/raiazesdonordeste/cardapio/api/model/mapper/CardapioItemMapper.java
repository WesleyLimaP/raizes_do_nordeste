package com.uninter.raiazesdonordeste.cardapio.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.app.dto.share.CardapioItemUnidadeDto;
import com.uninter.raiazesdonordeste.cardapio.api.app.dto.share.ProdutoUnidadeDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioItemPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemResponseDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.helper.CardapioMapperHelper;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.helper.ProdutoMapperHelper;
import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProdutoMapperHelper.class, CardapioMapperHelper.class})
public interface CardapioItemMapper {


    CardapioItemResponseDto toModel(CardapioItem cardapioItem);


    @Mapping(target = "cardapio", source = "cardapioId")
    @Mapping(target = "produto", source = "request.produtoId")
    CardapioItem toEntity(CardapioItemPostDto request, Long cardapioId);

    CardapioItemUnidadeDto toModelItemCardapio(CardapioItem cardapioItem);

    @Mapping(target = "precoBase", source = "precoBase")
    ProdutoUnidadeDto toProdutoUnidadeDto(Produto produto);

    List<CardapioItemDto> toCollectionModel(List<CardapioItem> itemEntity);
    List<CardapioItemUnidadeDto> toCollectionModelItemCardapio(List<CardapioItem> itemEntity);

}
