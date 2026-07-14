package com.uninter.raiazesdonordeste.pagamento.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Pagamento {
    @Id
    private Long id;
    private Long pedidoId;
    private String status;
    private String getwayExterno;
    private String referencialExterna;
    private Date dataHoraAtualizacao;
}
