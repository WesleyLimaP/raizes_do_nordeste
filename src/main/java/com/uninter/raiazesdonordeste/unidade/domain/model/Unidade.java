package com.uninter.raiazesdonordeste.unidade.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "tb_unidade")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String regiao;
    private String tipoCozinha;
    @Transient
    private List<Long> cardapiosIds;
    @Transient
    private List<Long> usuariosIds;
}
