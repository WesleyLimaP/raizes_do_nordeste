package com.uninter.raiazesdonordeste.cardapio.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.ItemPrecoLocalDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioItemPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioUpdateDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemMaxDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioMaxDto;
import com.uninter.raiazesdonordeste.cardapio.domain.service.CardapioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {
    @Autowired
    private CardapioService cardapioService;


    @GetMapping
    public ResponseEntity<List<CardapioResumoDto>> findAll() {
        return ResponseEntity.ok().body(cardapioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioMaxDto> findById(@PathVariable Long id) {
        CardapioMaxDto cardapio = cardapioService.findById(id);
        return ResponseEntity.ok(cardapio);
    }
    @PostMapping
    public ResponseEntity<CardapioMaxDto> save(@RequestBody @Valid CardapioPostDto cardapio) {
        CardapioMaxDto cardapioMaxDto = cardapioService.save(cardapio);
        var uri = UriBuilder.create(cardapioMaxDto.id());
        return ResponseEntity.created(uri).body(cardapioMaxDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CardapioMaxDto> update(@PathVariable Long id, @Valid @RequestBody CardapioUpdateDto cardapio) {
        var response = cardapioService.update(id, cardapio);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardapioService.delete(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * recursos de items de cardapio -----------------------------------------------------------------
     */
    @GetMapping("/{cardapioId}/items")
    public ResponseEntity<List<CardapioItemResumoDto>> findItems(@PathVariable Long cardapioId) {
        return ResponseEntity.ok().body(cardapioService.findByItems(cardapioId));
    }
    @PostMapping("/{cardapioId}/items")
    public ResponseEntity<CardapioItemMaxDto> saveItem(@PathVariable Long cardapioId, @RequestBody CardapioItemPostDto request) {
        var response = cardapioService.saveItem(cardapioId, request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }
    @DeleteMapping("/{cardapioId}/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long cardapioId, @PathVariable Long id) {
        cardapioService.deleteItem(cardapioId, id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{cardapioId}/items/{cardapioItemId}")
    public ResponseEntity<CardapioItemMaxDto> findItemById(@PathVariable Long cardapioId, @PathVariable Long cardapioItemId) {
        return ResponseEntity.ok().body(cardapioService.findItemById(cardapioId, cardapioItemId));
    }
    @PutMapping("/{cardapioId}/items/{id}")
    public ResponseEntity<CardapioItemMaxDto> updateItem(@PathVariable Long cardapioId, @PathVariable Long id, @RequestBody ItemPrecoLocalDto precoLocal) {
        return ResponseEntity.ok().body(cardapioService.updateItem(cardapioId, id, precoLocal));
    }
    @PutMapping("/{cardapioId}/items/{id}/disponivel")
    public ResponseEntity<Void> updateDisponibilidade(@PathVariable Long cardapioId, @PathVariable Long id) {
         cardapioService.disponibilizarItem(cardapioId, id);
         return ResponseEntity.noContent().build();
    }
    @PutMapping("/{cardapioId}/items/{id}/indisponivel")
    public ResponseEntity<Void> updateIndisponibilidade(@PathVariable Long cardapioId, @PathVariable Long id) {
         cardapioService.indisponibilizarItem(cardapioId, id);
         return ResponseEntity.noContent().build();
    }
}
