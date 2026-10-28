package com.uninter.raiazesdonordeste.lgpd.api.model.mapper;

import com.uninter.raiazesdonordeste.lgpd.api.model.dto.request.ConsentimentoLgpdPostDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdMaxDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;
import com.uninter.raiazesdonordeste.lgpd.domain.model.ConsentimentoLgpd;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsentimentoLgpdMapper {
    ConsentimentoLgpdMaxDto toModel(ConsentimentoLgpd consentimentoLgpd);

    ConsentimentoLgpd toEntity(ConsentimentoLgpdPostDto request);

    List<ConsentimentoLgpdResumoDto> toCollectionModel(List<ConsentimentoLgpd> consentimentosLgpd);
}
