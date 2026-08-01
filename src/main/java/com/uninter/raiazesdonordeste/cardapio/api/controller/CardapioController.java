package com.uninter.raiazesdonordeste.cardapio.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.ItemPrecoLocalDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioItemPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioUpdateDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioItemResponseDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResponseMinDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.request.CardapioPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.response.CardapioResponseDto;
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
    public ResponseEntity<List<CardapioResponseMinDto>> findAll() {
        return ResponseEntity.ok().body(cardapioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDto> findById(@PathVariable Long id) {
        CardapioResponseDto cardapio = cardapioService.findById(id);
        return ResponseEntity.ok(cardapio);
    }
    @PostMapping
    public ResponseEntity<CardapioResponseDto> save(@RequestBody @Valid CardapioPostDto cardapio) {
        CardapioResponseDto cardapioResponseDto = cardapioService.save(cardapio);
        var uri = UriBuilder.create(cardapioResponseDto.id());
        return ResponseEntity.created(uri).body(cardapioResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CardapioResponseDto> update(@PathVariable Long id, @Valid @RequestBody CardapioUpdateDto cardapio) {
        var response = cardapioService.update(id, cardapio);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardapioService.delete(id);
    }


    /**
     * recursos de items de cardapio -----------------------------------------------------------------
     */
    @GetMapping("/{cardapioId}/items")
    public ResponseEntity<List<CardapioItemDto>> findItems(@PathVariable Long cardapioId) {
        return ResponseEntity.ok().body(cardapioService.findByItems(cardapioId));
    }
    @PostMapping("/{cardapioId}/items")
    public ResponseEntity<CardapioItemResponseDto> saveItem(@PathVariable Long cardapioId, @RequestBody CardapioItemPostDto request) {
        var response = cardapioService.saveItem(cardapioId, request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }
    @DeleteMapping("/{cardapioId}/items/{id}")
    public void deleteItem(@PathVariable Long cardapioId, @PathVariable Long id) {
        cardapioService.deleteItem(cardapioId, id);
    }
    @GetMapping("/{cardapioId}/items/{cardapioItemId}")
    public ResponseEntity<CardapioItemResponseDto> findItemById(@PathVariable Long cardapioId, @PathVariable Long cardapioItemId) {
        return ResponseEntity.ok().body(cardapioService.findItemById(cardapioId, cardapioItemId));
    }
    @PutMapping("/{cardapioId}/items/{id}")
    public ResponseEntity<CardapioItemResponseDto> updateItem(@PathVariable Long cardapioId, @PathVariable Long id, @RequestBody ItemPrecoLocalDto precoLocal) {
        return ResponseEntity.ok().body(cardapioService.updateItem(cardapioId, id, precoLocal));
    }
    @PutMapping("/{cardapioId}/items/{id}/disponivel")
    public void updateDisponibilidade(@PathVariable Long cardapioId, @PathVariable Long id) {
         cardapioService.disponibilizarItem(cardapioId, id);
    }
    @PutMapping("/{cardapioId}/items/{id}/indisponivel")
    public void updateIndisponibilidade(@PathVariable Long cardapioId, @PathVariable Long id) {
         cardapioService.indisponibilizarItem(cardapioId, id);
    }
}
