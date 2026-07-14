package com.uninter.raiazesdonordeste.unidade.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Unidade {
    @Id
    private Long id;
    private String nome;
    private String endereco;
    private String regiao;
    private String tipoCozinha;
}
