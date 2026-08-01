package com.uninter.raiazesdonordeste.estoque.domain.listener;

import com.uninter.raiazesdonordeste.cardapio.domain.events.CardapioItemCriadoEvent;
import com.uninter.raiazesdonordeste.estoque.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueListener {
    @Autowired
    private EstoqueService estoqueService;

    @EventListener
    public void criarEstoque(CardapioItemCriadoEvent event){
        estoqueService.criarEstoque(event.unidadeId(), event.produtoId());

    }

}
