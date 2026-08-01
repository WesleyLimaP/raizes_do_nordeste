package com.uninter.raiazesdonordeste.cardapio.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.UnidadeMinDto;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {

    UnidadeMinDto toUnidadeMinDto(Unidade unidade);
}
