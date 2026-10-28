package com.uninter.raiazesdonordeste.fidelidade.api.model.mapper;

import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.ContaFidelidadePostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.model.ContaFidelidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContaFidelidadeMapper {
    ContaFidelidadeMaxDto toModel(ContaFidelidade contaFidelidade);

    ContaFidelidade toEntity(ContaFidelidadePostDto request);

    List<ContaFidelidadeResumoDto> toCollectionModel(List<ContaFidelidade> contasFidelidade);
}
