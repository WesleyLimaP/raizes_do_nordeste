package com.uninter.raiazesdonordeste.pedido.api.model.mapper;

import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.ProdutoMapper;
import com.uninter.raiazesdonordeste.cardapio.api.model.mapper.helper.ProdutoMapperHelper;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.ItemPedidoPostDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.ItemPedidoMaxDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.ItemPedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.api.model.mapper.helper.PedidoMapperHelper;
import com.uninter.raiazesdonordeste.pedido.domain.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProdutoMapper.class, ProdutoMapperHelper.class, PedidoMapperHelper.class})
public interface ItemPedidoMapper {
    ItemPedidoMaxDto toModel(ItemPedido itemPedido);

    @Mapping(target = "pedido", source = "pedidoId")
    @Mapping(target = "produto", source = "request.produtoId")
    ItemPedido toEntity(ItemPedidoPostDto request, Long pedidoId);

    List<ItemPedidoResumoDto> toCollectionModel(List<ItemPedido> items);
}
