package com.uninter.raiazesdonordeste.cliente.api.model.mapper;

import com.uninter.raiazesdonordeste.cliente.api.model.dto.request.ClientePostDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.request.ClienteUpdateDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.response.ClienteMaxDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.response.ClienteResumoDto;
import com.uninter.raiazesdonordeste.cliente.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResumoDto toResumoModel(Cliente cliente);
    Cliente toEntity(ClientePostDto request);
    Cliente update(@MappingTarget Cliente cliente, ClienteUpdateDto request);
    List<ClienteResumoDto> toCollectionModel(List<Cliente> clientes);
}
