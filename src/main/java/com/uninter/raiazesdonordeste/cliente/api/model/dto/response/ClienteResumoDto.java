package com.uninter.raiazesdonordeste.cliente.api.model.dto.response;

import java.time.LocalDate;

public record ClienteResumoDto(Long id, String nome, String email, LocalDate dataNascimento, String cpf) {
}
