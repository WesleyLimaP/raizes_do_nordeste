package com.uninter.raiazesdonordeste.fidelidade.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.ContaFidelidadePostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.ContaFidelidadeUpdateDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.service.ContaFidelidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas-fidelidade")
public class ContaFidelidadeController {
    private final ContaFidelidadeService contaFidelidadeService;

    public ContaFidelidadeController(ContaFidelidadeService contaFidelidadeService) {
        this.contaFidelidadeService = contaFidelidadeService;
    }

    @GetMapping
    public ResponseEntity<List<ContaFidelidadeResumoDto>> findAll() {
        return ResponseEntity.ok(contaFidelidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaFidelidadeMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contaFidelidadeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContaFidelidadeMaxDto> save(@RequestBody @Valid ContaFidelidadePostDto request) {
        ContaFidelidadeMaxDto response = contaFidelidadeService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaFidelidadeMaxDto> update(@PathVariable Long id, @RequestBody @Valid ContaFidelidadeUpdateDto request) {
        return ResponseEntity.ok(contaFidelidadeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaFidelidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
