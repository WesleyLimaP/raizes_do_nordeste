package com.uninter.raiazesdonordeste.pagamento.api.model.mapper;

import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoPostDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoMaxDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoResumoDto;
import com.uninter.raiazesdonordeste.pagamento.domain.model.Pagamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    PagamentoMaxDto toModel(Pagamento pagamento);

    Pagamento toEntity(PagamentoPostDto request);

    List<PagamentoResumoDto> toCollectionModel(List<Pagamento> pagamentos);
}
