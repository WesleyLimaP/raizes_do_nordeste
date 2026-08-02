package com.uninter.raiazesdonordeste.unidade.api.model.mapper;

import com.uninter.raiazesdonordeste.unidade.api.model.dto.UnidadeMaxDto;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.UnidadePostDto;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.UnidadeResumoDto;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.UnidadeUpdateDto;
import com.uninter.raiazesdonordeste.unidade.domain.model.Unidade;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadeApiMapper {
    UnidadeMaxDto toModel(Unidade unidade);

    Unidade toEntity(UnidadePostDto unidadePostDto);

    UnidadeResumoDto toResumoModel(Unidade unidade);

    List<UnidadeResumoDto> toCollectionModel(List<Unidade> unidades);

    Unidade update(@MappingTarget Unidade unidade, UnidadeUpdateDto unidadeUpdateDto);
}
