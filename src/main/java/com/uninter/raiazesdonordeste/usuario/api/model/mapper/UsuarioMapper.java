package com.uninter.raiazesdonordeste.usuario.api.model.mapper;

import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.UsuarioMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.UsuarioResumoDto;
import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMaxDto toModel(Usuario usuario);

    Usuario toEntity(UsuarioPostDto request);

    List<UsuarioResumoDto> toCollectionModel(List<Usuario> usuarios);
}
