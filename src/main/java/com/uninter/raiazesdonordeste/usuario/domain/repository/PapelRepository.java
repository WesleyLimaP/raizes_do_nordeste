package com.uninter.raiazesdonordeste.usuario.domain.repository;

import com.uninter.raiazesdonordeste.usuario.domain.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
}
