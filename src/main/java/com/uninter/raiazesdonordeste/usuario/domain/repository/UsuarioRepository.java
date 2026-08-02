package com.uninter.raiazesdonordeste.usuario.domain.repository;

import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
