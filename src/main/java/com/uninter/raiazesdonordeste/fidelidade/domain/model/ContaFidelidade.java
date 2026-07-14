package com.uninter.raiazesdonordeste.fidelidade.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContaFidelidade {
    @Id
    private Long id;
    private Long pontosAcumulados;
    private Long clienteId;
}
