package com.uninter.raiazesdonordeste.lgpd.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.request.ConsentimentoLgpdPostDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.request.ConsentimentoLgpdUpdateDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdMaxDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;
import com.uninter.raiazesdonordeste.lgpd.domain.service.ConsentimentoLgpdService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consentimentos-lgpd")
public class ConsentimentoLgpdController {
    private final ConsentimentoLgpdService consentimentoLgpdService;

    public ConsentimentoLgpdController(ConsentimentoLgpdService consentimentoLgpdService) {
        this.consentimentoLgpdService = consentimentoLgpdService;
    }

    @GetMapping
    public ResponseEntity<List<ConsentimentoLgpdResumoDto>> findAll() {
        return ResponseEntity.ok(consentimentoLgpdService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsentimentoLgpdMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(consentimentoLgpdService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ConsentimentoLgpdMaxDto> save(@RequestBody @Valid ConsentimentoLgpdPostDto request) {
        ConsentimentoLgpdMaxDto response = consentimentoLgpdService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsentimentoLgpdMaxDto> update(@PathVariable Long id, @RequestBody @Valid ConsentimentoLgpdUpdateDto request) {
        return ResponseEntity.ok(consentimentoLgpdService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consentimentoLgpdService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
