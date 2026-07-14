package com.uninter.raiazesdonordeste.lgpd.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ConsentimentoLgpd {
    @Id
    private Long id;
    private Long clienteId;
    private String tipoConsentimento;
    private Boolean revogado;
    private LocalDate dataConsentimento;
}
