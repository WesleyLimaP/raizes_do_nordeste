package com.uninter.raiazesdonordeste.pedido.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Pedido {
    @Id
    private Long id;
    private String canal;
    private String status;
    private Date dataHora;
    private Long clienteId;
    private Long unidadeId;
}
