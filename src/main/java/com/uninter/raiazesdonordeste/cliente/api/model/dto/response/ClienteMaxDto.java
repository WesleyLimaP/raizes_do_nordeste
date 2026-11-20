package com.uninter.raiazesdonordeste.cliente.api.model.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ClienteMaxDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String cpf,
        List<Long> pedidosIds,
        Long contaFidelidadeId,
        List<Long> consentimentosLgpdIds,
        List<Long> campanhasFidelidadeIds
) {
}
