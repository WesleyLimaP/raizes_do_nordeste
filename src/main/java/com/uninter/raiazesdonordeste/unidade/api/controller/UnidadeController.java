package com.uninter.raiazesdonordeste.unidade.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.unidade.api.model.dto.*;
import com.uninter.raiazesdonordeste.unidade.domain.service.UnidadeService;
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

    @GetMapping("/{id}/cardapios")
    public ResponseEntity<List<CardapioUnidadeResumoDto>> findCardapiosByUnidadeId(@PathVariable Long id) {
        var cardapiosDto = unidadeService.findCardapios(id);
        return ResponseEntity.ok(cardapiosDto);
    }
    @GetMapping("/{id}/cardapios/{cardapioId}")
    public ResponseEntity<CardapioUnidadeMaxDto> findCardapioByIdAndUnidadeId(@PathVariable Long id, @PathVariable Long cardapioId) {
        var cardapiosDto = unidadeService.findCardapiosById(id, cardapioId);
        return ResponseEntity.ok(cardapiosDto);
    }


}
