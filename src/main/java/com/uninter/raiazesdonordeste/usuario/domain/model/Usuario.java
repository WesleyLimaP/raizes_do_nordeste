package com.uninter.raiazesdonordeste.usuario.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    private Long id;
    private String nome;
    private String login;
    private String senhahash;
    private Long unidadeId;
}
