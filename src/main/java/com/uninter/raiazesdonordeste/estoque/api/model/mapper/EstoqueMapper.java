package com.uninter.raiazesdonordeste.estoque.api.model.mapper;

import com.uninter.raiazesdonordeste.estoque.api.model.dto.request.EstoquePostDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueMaxDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueResumoDto;
import com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper.ProdutoHelper;
import com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper.UnidadeHelper;
import com.uninter.raiazesdonordeste.estoque.domain.model.Estoque;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UnidadeHelper.class, ProdutoHelper.class})
public interface EstoqueMapper  {

    @Mapping(target = "unidade", source = "unidadeId", qualifiedByName = "unidadeToResumoDto")
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoToResumoDto")
    EstoqueMaxDto toModel(Estoque estoque);
    EstoqueResumoDto toResumoModel(Estoque estoque);
    Estoque toEntity(EstoquePostDto estoquePostDto);
    List<EstoqueResumoDto> toCollectionModel(List<Estoque> estoques);

}
