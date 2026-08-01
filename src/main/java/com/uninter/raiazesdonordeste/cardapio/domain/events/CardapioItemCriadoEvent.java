package com.uninter.raiazesdonordeste.cardapio.domain.events;

import org.springframework.context.ApplicationEventPublisher;

public record CardapioItemCriadoEvent (Long unidadeId, Long produtoId) {

}
