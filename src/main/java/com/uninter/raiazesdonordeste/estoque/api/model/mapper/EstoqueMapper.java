package com.uninter.raiazesdonordeste.estoque.api.model.mapper;

import com.uninter.raiazesdonordeste.estoque.api.model.dto.request.EstoquePostDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueMinDto;
import com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper.ProdutoHelper;
import com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper.UnidadeHelper;
import com.uninter.raiazesdonordeste.estoque.domain.model.Estoque;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UnidadeHelper.class, ProdutoHelper.class})
public interface EstoqueMapper  {

    @Mapping(target = "unidade", source = "unidadeId", qualifiedByName = "unidadeToMinDto")
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoToMinDto")
    EstoqueDto toModel(Estoque estoque);
    EstoqueMinDto toMinDto(Estoque estoque);
    Estoque toEntity(EstoquePostDto estoquePostDto);
    List<EstoqueMinDto> toCollectionModel(List<Estoque> estoques);

}
