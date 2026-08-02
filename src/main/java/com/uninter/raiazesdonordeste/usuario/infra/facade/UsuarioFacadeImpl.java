package com.uninter.raiazesdonordeste.usuario.infra.facade;

import com.uninter.raiazesdonordeste.usuario.api.app.UsuarioFacade;
import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import com.uninter.raiazesdonordeste.usuario.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioFacadeImpl implements UsuarioFacade {
    @Autowired
    private UsuarioRepository usuarioRepository;


}
