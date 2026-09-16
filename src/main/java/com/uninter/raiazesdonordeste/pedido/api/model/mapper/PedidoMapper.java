package com.uninter.raiazesdonordeste.pedido.api.model.mapper;

import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.PedidoPostDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoMaxDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoMaxDto toModel(Pedido pedido);

    Pedido toEntity(PedidoPostDto request);

    List<PedidoResumoDto> toCollectionModel(List<Pedido> pedidos);
}
