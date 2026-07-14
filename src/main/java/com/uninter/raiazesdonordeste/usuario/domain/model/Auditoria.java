package com.uninter.raiazesdonordeste.usuario.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Auditoria {
    @Id
    private Long id;
    private String acao;
    private String entidadeAfetada;
    private Date dataHora;
    private String detalhes;
    private Long usuarioId;
}
