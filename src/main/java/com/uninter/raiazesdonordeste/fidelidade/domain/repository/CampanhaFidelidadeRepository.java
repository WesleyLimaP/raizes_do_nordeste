package com.uninter.raiazesdonordeste.fidelidade.domain.repository;

import com.uninter.raiazesdonordeste.fidelidade.domain.model.CampanhaFidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaFidelidadeRepository extends JpaRepository<CampanhaFidelidade, Long> {
}
