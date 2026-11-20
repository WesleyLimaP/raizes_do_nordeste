package com.uninter.raiazesdonordeste.cliente.api.model.dto.request;

import java.time.LocalDate;

public record ClientePostDto(String nome, String email, LocalDate dataNascimento, String cpf) {
}
