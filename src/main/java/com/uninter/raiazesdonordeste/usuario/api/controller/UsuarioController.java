package com.uninter.raiazesdonordeste.usuario.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaResumoDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioUpdateDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.UsuarioMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.UsuarioResumoDto;
import com.uninter.raiazesdonordeste.usuario.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResumoDto>> findAll(@RequestParam(required = false) Long unidadeId) {
        return ResponseEntity.ok(usuarioService.findAll(unidadeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioMaxDto> save(@RequestBody @Valid UsuarioPostDto request) {
        UsuarioMaxDto response = usuarioService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioMaxDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateDto request) {
        return ResponseEntity.ok(usuarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/auditorias")
    public ResponseEntity<List<AuditoriaResumoDto>> findAuditorias(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findAuditoriasByUsuarioId(id));
    }
}
