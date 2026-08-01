package com.uninter.raiazesdonordeste.cardapio.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoMaxDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoUpdateDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.ProdutoDto;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {


    ProdutoMaxDto toModel(Produto produto);
    Produto toEntity(ProdutoPostDto produtoPostDto);
    ProdutoResumoDto toResumoModel(Produto produto);
    List<ProdutoResumoDto> toCollectionModel(List<Produto> produtos);
    Produto update(@MappingTarget Produto produto, ProdutoUpdateDto produtoUpdateDto);

}
