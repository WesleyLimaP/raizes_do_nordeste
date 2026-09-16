package com.uninter.raiazesdonordeste.usuario.domain.repository;

import com.uninter.raiazesdonordeste.usuario.domain.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
}
