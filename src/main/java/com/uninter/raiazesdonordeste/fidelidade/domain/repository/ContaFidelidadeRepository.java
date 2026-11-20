package com.uninter.raiazesdonordeste.fidelidade.domain.repository;

import com.uninter.raiazesdonordeste.fidelidade.domain.model.ContaFidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaFidelidadeRepository extends JpaRepository<ContaFidelidade, Long> {
    Optional<ContaFidelidade> findByClienteId(Long clienteId);
}
