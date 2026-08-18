package com.uninter.raiazesdonordeste.usuario.api.app;

import com.uninter.raiazesdonordeste.usuario.api.app.dto.share.UsuarioUnidadeDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioFacade {
    List<UsuarioUnidadeDto> findUsuariosDaUnidade(Long unidadeId);
    Optional<UsuarioUnidadeDto> findUsuarioDaUnidade(Long unidadeId, Long usuarioId);
}
