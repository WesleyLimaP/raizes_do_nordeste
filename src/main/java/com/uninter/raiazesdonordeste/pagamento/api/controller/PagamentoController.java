package com.uninter.raiazesdonordeste.pagamento.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoPostDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoUpdateDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoMaxDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoResumoDto;
import com.uninter.raiazesdonordeste.pagamento.domain.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResumoDto>> findAll() {
        return ResponseEntity.ok(pagamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoMaxDto> save(@RequestBody @Valid PagamentoPostDto request) {
        PagamentoMaxDto response = pagamentoService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoMaxDto> update(@PathVariable Long id, @RequestBody @Valid PagamentoUpdateDto request) {
        return ResponseEntity.ok(pagamentoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
