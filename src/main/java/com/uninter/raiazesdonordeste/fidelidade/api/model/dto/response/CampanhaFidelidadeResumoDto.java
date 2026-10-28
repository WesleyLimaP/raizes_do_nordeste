package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response;

public record CampanhaFidelidadeResumoDto(
        Long id,
        String nome,
        String criterioSegmentacao,
        Double desconto
) {
}
