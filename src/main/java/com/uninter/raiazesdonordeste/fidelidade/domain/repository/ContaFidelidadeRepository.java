package com.uninter.raiazesdonordeste.fidelidade.domain.repository;

import com.uninter.raiazesdonordeste.fidelidade.domain.model.ContaFidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaFidelidadeRepository extends JpaRepository<ContaFidelidade, Long> {
}
