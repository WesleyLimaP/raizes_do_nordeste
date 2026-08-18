package com.uninter.raiazesdonordeste.usuario.domain.repository;

import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByUnidadeId(Long unidadeId);
    Optional<Usuario> findByUnidadeIdAndId(Long unidadeId, Long usuarioId);
}
