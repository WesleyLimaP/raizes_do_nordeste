package com.uninter.raiazesdonordeste.usuario.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senhahash;
    private Long unidadeId;
    @OneToMany(mappedBy = "usuario")
    private List<Auditoria> auditorias;
    @ManyToMany
    @JoinTable(
            name = "tb_usuario_papel",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "papel_id")

    )
    private List<Papel> papeis;
}
