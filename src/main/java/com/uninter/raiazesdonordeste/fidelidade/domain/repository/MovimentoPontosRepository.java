package com.uninter.raiazesdonordeste.fidelidade.domain.repository;

import com.uninter.raiazesdonordeste.fidelidade.domain.model.MovimentoPontos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoPontosRepository extends JpaRepository<MovimentoPontos, Long> {
}
