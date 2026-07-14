package com.uninter.raiazesdonordeste.fidelidade.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CampanhaFidelidade {
    @Id
    private Long id;
    private String nome;
    private String criterioSegmentacao;
    private Double desconto;
}
