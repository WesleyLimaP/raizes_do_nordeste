package com.uninter.raiazesdonordeste.usuario.infra.facade;

import com.uninter.raiazesdonordeste.usuario.api.app.UsuarioFacade;
import com.uninter.raiazesdonordeste.usuario.api.app.dto.share.UsuarioUnidadeDto;
import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import com.uninter.raiazesdonordeste.usuario.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioFacadeImpl implements UsuarioFacade {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioUnidadeDto> findUsuariosDaUnidade(Long unidadeId) {
        return usuarioRepository.findAllByUnidadeId(unidadeId)
                .stream()
                .map(this::toUsuarioUnidadeDto)
                .toList();
    }

    @Override
    public Optional<UsuarioUnidadeDto> findUsuarioDaUnidade(Long unidadeId, Long usuarioId) {
        return usuarioRepository.findByUnidadeIdAndId(unidadeId, usuarioId)
                .map(this::toUsuarioUnidadeDto);
    }

    private UsuarioUnidadeDto toUsuarioUnidadeDto(Usuario usuario) {
        return new UsuarioUnidadeDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getUnidadeId()
        );
    }
}
