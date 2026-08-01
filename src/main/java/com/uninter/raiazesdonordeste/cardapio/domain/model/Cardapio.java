package com.uninter.raiazesdonordeste.cardapio.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_cardapio")
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate vigenciaInicio;
    private LocalDate vigenciaFim;
    private Long unidadeId;
    @OneToMany(mappedBy = "cardapio")
    private List<CardapioItem> items = new ArrayList<>();

}
