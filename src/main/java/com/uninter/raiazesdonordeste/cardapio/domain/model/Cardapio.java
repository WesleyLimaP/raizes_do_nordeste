package com.uninter.raiazesdonordeste.cardapio.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Cardapio {
    @Id
    private Long id;
    private LocalDate vigenciaInicio;
    private LocalDate vigenciaFim;
    private Long unidadeId;
}
