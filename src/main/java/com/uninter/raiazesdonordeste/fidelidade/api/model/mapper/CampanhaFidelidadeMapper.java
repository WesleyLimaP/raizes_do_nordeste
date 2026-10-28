package com.uninter.raiazesdonordeste.fidelidade.api.model.mapper;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.CampanhaFidelidadePostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.model.CampanhaFidelidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampanhaFidelidadeMapper {
    CampanhaFidelidadeMaxDto toModel(CampanhaFidelidade campanhaFidelidade);

    CampanhaFidelidade toEntity(CampanhaFidelidadePostDto request);

    List<CampanhaFidelidadeResumoDto> toCollectionModel(List<CampanhaFidelidade> campanhasFidelidade);
}
