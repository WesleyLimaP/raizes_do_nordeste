package com.uninter.raiazesdonordeste.usuario.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.PapelPostDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.PapelUpdateDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.PapelMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.response.PapelResumoDto;
import com.uninter.raiazesdonordeste.usuario.domain.service.PapelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papeis")
public class PapelController {
    private final PapelService papelService;

    public PapelController(PapelService papelService) {
        this.papelService = papelService;
    }

    @GetMapping
    public ResponseEntity<List<PapelResumoDto>> findAll() {
        return ResponseEntity.ok(papelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PapelMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(papelService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PapelMaxDto> save(@RequestBody @Valid PapelPostDto request) {
        PapelMaxDto response = papelService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PapelMaxDto> update(@PathVariable Long id, @RequestBody @Valid PapelUpdateDto request) {
        return ResponseEntity.ok(papelService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        papelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
