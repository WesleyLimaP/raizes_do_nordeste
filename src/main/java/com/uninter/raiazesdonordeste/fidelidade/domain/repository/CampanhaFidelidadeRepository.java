package com.uninter.raiazesdonordeste.fidelidade.domain.repository;

import com.uninter.raiazesdonordeste.fidelidade.domain.model.CampanhaFidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CampanhaFidelidadeRepository extends JpaRepository<CampanhaFidelidade, Long> {
    @org.springframework.data.jpa.repository.Query(value = "select c.* from tb_campanha_fidelidade c join tb_campanha_fidelidade_cliente cc on cc.camapanha_fidelidade_id = c.id where cc.cliente_id = :clienteId", nativeQuery = true)
    List<CampanhaFidelidade> findByClienteId(@Param("clienteId") Long clienteId);
}
