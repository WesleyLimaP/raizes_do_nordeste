package com.uninter.raiazesdonordeste.lgpd.domain.repository;

import com.uninter.raiazesdonordeste.lgpd.domain.model.ConsentimentoLgpd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentimentoLgpdRepository extends JpaRepository<ConsentimentoLgpd, Long> {
}
