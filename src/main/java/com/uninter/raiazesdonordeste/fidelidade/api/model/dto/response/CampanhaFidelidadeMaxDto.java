package com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response;

public record CampanhaFidelidadeMaxDto(
        Long id,
        String nome,
        String criterioSegmentacao,
        Double desconto
) {
}
