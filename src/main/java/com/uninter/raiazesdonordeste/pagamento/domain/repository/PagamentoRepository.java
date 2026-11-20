package com.uninter.raiazesdonordeste.pagamento.domain.repository;

import com.uninter.raiazesdonordeste.pagamento.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByReferencialExterna(String referencialExterna);
}
