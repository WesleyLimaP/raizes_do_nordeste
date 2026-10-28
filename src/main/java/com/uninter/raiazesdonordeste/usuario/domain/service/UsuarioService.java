package com.uninter.raiazesdonordeste.usuario.domain.service;

import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaResumoDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioPapelPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioUpdateDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.UsuarioMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.UsuarioResumoDto;
import com.uninter.raiazesdonordeste.usuario.api.model.mapper.AuditoriaMapper;
import com.uninter.raiazesdonordeste.usuario.api.model.mapper.UsuarioMapper;
import com.uninter.raiazesdonordeste.usuario.domain.model.Papel;
import com.uninter.raiazesdonordeste.usuario.domain.model.Usuario;
import com.uninter.raiazesdonordeste.usuario.domain.repository.AuditoriaRepository;
import com.uninter.raiazesdonordeste.usuario.domain.repository.PapelRepository;
import com.uninter.raiazesdonordeste.usuario.domain.repository.UsuarioRepository;
import com.uninter.raiazesdonordeste.usuario.exceptions.PapelNotFoundException;
import com.uninter.raiazesdonordeste.usuario.exceptions.UsuarioNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private  UsuarioMapper usuarioMapper;
    @Autowired
    private  AuditoriaRepository auditoriaRepository;
    @Autowired
    private  AuditoriaMapper auditoriaMapper;
    @Autowired
    private PapelRepository papelRepository;


    @Transactional(readOnly = true)
    public List<UsuarioResumoDto> findAll(Long unidadeId) {
        if (unidadeId != null) {
            return usuarioMapper.toCollectionModel(usuarioRepository.findAllByUnidadeId(unidadeId));
        }
        return usuarioMapper.toCollectionModel(usuarioRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UsuarioMaxDto findById(Long id) {
        return usuarioMapper.toModel(getUsuarioOrElseThrow(id));
    }

    @Transactional
    public UsuarioMaxDto save(UsuarioPostDto request) {
        Usuario usuario = usuarioMapper.toEntity(request);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toModel(usuario);
    }

    @Transactional
    public UsuarioMaxDto update(Long id, UsuarioUpdateDto request) {
        Usuario usuario = getUsuarioOrElseThrow(id);
        usuario.setNome(request.nome());
        usuario.setLogin(request.login());
        usuario.setSenhahash(request.senhahash());
        usuario.setUnidadeId(request.unidadeId());
        return usuarioMapper.toModel(usuario);
    }

    @Transactional
    public void delete(Long id) {
        getUsuarioOrElseThrow(id);
        usuarioRepository.deleteById(id);
    }

    private Usuario getUsuarioOrElseThrow(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario nao encontrado com id: " + usuarioId));
    }

    @Transactional(readOnly = true)
    public List<AuditoriaResumoDto> findAuditoriasByUsuarioId(Long usuarioId) {
        getUsuarioOrElseThrow(usuarioId);
        return auditoriaMapper.toCollectionModel(auditoriaRepository.findByUsuarioId(usuarioId));
    }

    @Transactional
    public void associarPapel(@Valid UsuarioPapelPostDto request) {
        Usuario usuario = getUsuarioOrElseThrow(request.usuarioId());
        Papel papel = getPapelOrElseThrow(request.papelId());
        usuario.getPapeis().add(papel);
    }

    private Papel getPapelOrElseThrow(@NotNull Long papelId) {
        return papelRepository.findById(papelId)
                .orElseThrow(() -> new PapelNotFoundException("Papel nao encontrado com id: " + papelId));
    }

    @Transactional
    public void desassociarPapel(Long id) {
        Usuario usuario = getUsuarioOrElseThrow(id);
        usuario.getPapeis().clear();
    }
}
