package com.uninter.raiazesdonordeste.fidelidade.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.MovimentoPontosPostDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.request.MovimentoPontosUpdateDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.MovimentoPontosMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.MovimentoPontosResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.domain.service.MovimentoPontosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentos-pontos")
public class MovimentoPontosController {
    private final MovimentoPontosService movimentoPontosService;

    public MovimentoPontosController(MovimentoPontosService movimentoPontosService) {
        this.movimentoPontosService = movimentoPontosService;
    }

    @GetMapping
    public ResponseEntity<List<MovimentoPontosResumoDto>> findAll() {
        return ResponseEntity.ok(movimentoPontosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoPontosMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movimentoPontosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovimentoPontosMaxDto> save(@RequestBody @Valid MovimentoPontosPostDto request) {
        MovimentoPontosMaxDto response = movimentoPontosService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentoPontosMaxDto> update(@PathVariable Long id, @RequestBody @Valid MovimentoPontosUpdateDto request) {
        return ResponseEntity.ok(movimentoPontosService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimentoPontosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
