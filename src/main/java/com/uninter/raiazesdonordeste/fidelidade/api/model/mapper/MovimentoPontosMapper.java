package com.uninter.raiazesdonordeste.fidelidade.api.model.mapper;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.MovimentoPontosPostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.MovimentoPontosMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.MovimentoPontosResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.model.MovimentoPontos;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimentoPontosMapper {
    MovimentoPontosMaxDto toModel(MovimentoPontos movimentoPontos);

    MovimentoPontos toEntity(MovimentoPontosPostDto request);

    List<MovimentoPontosResumoDto> toCollectionModel(List<MovimentoPontos> movimentosPontos);
}
