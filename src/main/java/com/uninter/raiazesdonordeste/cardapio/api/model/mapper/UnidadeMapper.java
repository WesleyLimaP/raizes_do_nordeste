package com.uninter.raiazesdonordeste.cardapio.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.UnidadeResumoDto;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {

    UnidadeResumoDto toUnidadeResumoDto(Unidade unidade);
}
