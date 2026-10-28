package com.uninter.raiazesdonordeste.usuario.api.model.mapper;

import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.PapelPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.PapelMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.PapelResumoDto;
import com.uninter.raiazesdonordeste.usuario.domain.model.Papel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PapelMapper {
    PapelMaxDto toModel(Papel papel);

    Papel toEntity(PapelPostDto request);

    List<PapelResumoDto> toCollectionModel(List<Papel> papeis);
}
