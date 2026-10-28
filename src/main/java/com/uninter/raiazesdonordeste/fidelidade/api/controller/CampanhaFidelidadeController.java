package com.uninter.raiazesdonordeste.fidelidade.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.CampanhaFidelidadePostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.CampanhaFidelidadeUpdateDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.service.CampanhaFidelidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campanhas-fidelidade")
public class CampanhaFidelidadeController {
    private final CampanhaFidelidadeService campanhaFidelidadeService;

    public CampanhaFidelidadeController(CampanhaFidelidadeService campanhaFidelidadeService) {
        this.campanhaFidelidadeService = campanhaFidelidadeService;
    }

    @GetMapping
    public ResponseEntity<List<CampanhaFidelidadeResumoDto>> findAll() {
        return ResponseEntity.ok(campanhaFidelidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaFidelidadeMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaFidelidadeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CampanhaFidelidadeMaxDto> save(@RequestBody @Valid CampanhaFidelidadePostDto request) {
        CampanhaFidelidadeMaxDto response = campanhaFidelidadeService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaFidelidadeMaxDto> update(@PathVariable Long id, @RequestBody @Valid CampanhaFidelidadeUpdateDto request) {
        return ResponseEntity.ok(campanhaFidelidadeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        campanhaFidelidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
