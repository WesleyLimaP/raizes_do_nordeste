package com.uninter.raiazesdonordeste.usuario.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String acao;
    private String entidadeAfetada;
    private Date dataHora;
    private String detalhes;
    private Long usuarioId;
}
