package com.uninter.raiazesdonordeste.lgpd.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_consentimento_lgpd")
public class ConsentimentoLgpd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private String tipoConsentimento;
    private Boolean revogado;
    private LocalDate dataConsentimento;
}
