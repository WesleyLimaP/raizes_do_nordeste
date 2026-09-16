package com.uninter.raiazesdonordeste.pedido.api.model.dto.response;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.ProdutoCardapioResumoDto;

public record ItemPedidoMaxDto(
        Long id,
        Double precoUnitario,
        Long quantidade,
        ProdutoCardapioResumoDto produto
) {
}
