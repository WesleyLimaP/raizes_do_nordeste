package com.uninter.raiazesdonordeste.cardapio.api.model.mapper.helper;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.cardapio.domain.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardapioMapperHelper {
    @Autowired
    CardapioRepository cardapioRepository;

    public Cardapio findCardapioById(Long id) {
        return cardapioRepository.findById(id).orElse(null);
    }

}
