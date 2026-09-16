package com.uninter.raiazesdonordeste.pedido.api.model.dto.response;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.ProdutoCardapioResumoDto;

public record ItemPedidoResumoDto(
        Long id,
        Double precoUnitario,
        Long quantidade,
        ProdutoCardapioResumoDto produto
) {
}
