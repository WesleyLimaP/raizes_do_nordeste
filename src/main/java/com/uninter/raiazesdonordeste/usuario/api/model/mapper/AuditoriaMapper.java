package com.uninter.raiazesdonordeste.usuario.api.model.mapper;

import com.uninter.raiazesdonordeste.usuario.api.app.dto.share.UsuarioUnidadeDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaResumoDto;
import com.uninter.raiazesdonordeste.usuario.domain.model.Auditoria;
import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditoriaMapper {

    @Mapping(target = "usuario", source = "usuario")
    AuditoriaMaxDto toModel(Auditoria auditoria);

    @Mapping(target = "usuarioId", source = "usuario.id")
    AuditoriaResumoDto toResumoModel(Auditoria auditoria);

    List<AuditoriaResumoDto> toCollectionModel(List<Auditoria> auditorias);

    default UsuarioUnidadeDto toUsuarioUnidadeDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioUnidadeDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getUnidadeId()
        );
    }
}
