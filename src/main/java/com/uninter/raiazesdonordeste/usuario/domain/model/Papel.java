package com.uninter.raiazesdonordeste.usuario.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Papel {
    @Id
    private Long id;
    private String descricao;
}
