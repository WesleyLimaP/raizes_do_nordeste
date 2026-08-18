package com.uninter.raiazesdonordeste.unidade.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.*;
import com.uninter.raiazesdonordeste.unidade.domain.service.UnidadeService;
import com.uninter.raiazesdonordeste.usuario.api.app.dto.share.UsuarioUnidadeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {
    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<UnidadeResumoDto>> findAll() {
        return ResponseEntity.ok(unidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeMaxDto> findById(@PathVariable Long id) {
        var unidadeDto = unidadeService.findById(id);
        return ResponseEntity.ok(unidadeDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeMaxDto> save(@RequestBody UnidadePostDto unidadePostDto) {
        var unidadeDto = unidadeService.save(unidadePostDto);
        var uri = UriBuilder.create(unidadeDto.id());
        return ResponseEntity.created(uri).body(unidadeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeMaxDto> update(@PathVariable Long id, @RequestBody UnidadeUpdateDto unidadeUpdateDto) {
        var unidadeDto = unidadeService.update(id, unidadeUpdateDto);
        return ResponseEntity.ok(unidadeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{unidadeId}/cardapios")
    public ResponseEntity<List<CardapioUnidadeResumoDto>> findCardapiosDaUnidade(@PathVariable Long unidadeId) {
        var cardapiosDto = unidadeService.findCardapiosDaUnidade(unidadeId);
        return ResponseEntity.ok(cardapiosDto);
    }

    @GetMapping("/{unidadeId}/cardapios/{cardapioId}")
    public ResponseEntity<CardapioUnidadeMaxDto> findCardapioDaUnidade(@PathVariable Long unidadeId, @PathVariable Long cardapioId) {
        var cardapioDto = unidadeService.findCardapioDaUnidade(unidadeId, cardapioId);
        return ResponseEntity.ok(cardapioDto);
    }

    @GetMapping("/{unidadeId}/usuarios")
    public ResponseEntity<List<UsuarioUnidadeDto>> findUsuariosDaUnidade(@PathVariable Long unidadeId) {
        var usuariosDto = unidadeService.findUsuariosDaUnidade(unidadeId);
        return ResponseEntity.ok(usuariosDto);
    }

    @GetMapping("/{unidadeId}/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioUnidadeDto> findUsuarioDaUnidade(@PathVariable Long unidadeId, @PathVariable Long usuarioId) {
        var usuarioDto = unidadeService.findUsuarioDaUnidade(unidadeId, usuarioId);
        return ResponseEntity.ok(usuarioDto);
    }


}
