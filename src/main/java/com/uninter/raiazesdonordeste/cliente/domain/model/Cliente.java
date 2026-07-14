package com.uninter.raiazesdonordeste.cliente.domain.model;

import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
}
