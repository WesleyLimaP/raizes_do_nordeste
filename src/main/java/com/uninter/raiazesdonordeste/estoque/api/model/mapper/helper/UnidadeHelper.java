package com.uninter.raiazesdonordeste.estoque.api.model.mapper.helper;

import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.UnidadeMinDto;
import com.uninter.raiazesdonordeste.unidade.api.app.UnidadeFacade;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadeHelper {
    @Autowired
    UnidadeFacade unidadeFacade;

    @Named("unidadeToMinDto")
    public UnidadeMinDto toMinDto(Long unidadeId) {
        var unidade = unidadeFacade.findById(unidadeId).get();
        return new UnidadeMinDto(unidade.getId(), unidade.getNome());
    }


}
