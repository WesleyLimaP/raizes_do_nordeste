package com.uninter.raiazesdonordeste.cardapio.api.controller;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
import com.uninter.raiazesdonordeste.cardapio.domain.model.CardapioItem;
import com.uninter.raiazesdonordeste.cardapio.domain.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {
    @Autowired
    private CardapioService cardapioService;


    @GetMapping
    public List<Cardapio> findAll() {
        return cardapioService.findAll();
    }

    @GetMapping("/{id}")
    public Cardapio findById(@PathVariable Long id) {
        return cardapioService.findById(id);
    }
    @PostMapping
    public Cardapio save(@RequestBody Cardapio cardapio) {
        return cardapioService.save(cardapio);
    }
    @PutMapping("/{id}")
    public Cardapio update(@PathVariable Long id, @RequestBody Cardapio cardapio) {
        return cardapioService.update(id, cardapio);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardapioService.delete(id);
    }


    /**
     * recursos de items de cardapio -----------------------------------------------------------------
     */
    @GetMapping("/{cardapioId}/items")
    public List<CardapioItem> findItems(@PathVariable Long cardapioId) {
        return cardapioService.findByItems(cardapioId);
    }
    @PostMapping("/{cardapioId}/items")
    public CardapioItem saveItem(@PathVariable Long cardapioId, @RequestBody CardapioItem cardapioItem) {
        return cardapioService.saveItem(cardapioId, cardapioItem);
    }
    @DeleteMapping("/{cardapioId}/items/{id}")
    public void deleteItem(@PathVariable Long cardapioId, @PathVariable Long id) {
        cardapioService.deleteItem(cardapioId, id);
    }
    @GetMapping("/{cardapioId}/items/{cardapioItemId}")
    public CardapioItem findItemById(@PathVariable Long cardapioId, @PathVariable Long cardapioItemId) {
        return cardapioService.findItemById(cardapioId, cardapioItemId);
    }
    @PutMapping("/{cardapioId}/items/{id}")
    public CardapioItem updateItem(@PathVariable Long cardapioId, @PathVariable Long id, @RequestBody CardapioItem cardapioItem) {
        return cardapioService.updateItem(cardapioId, id, cardapioItem);
    }
    @PutMapping("/{cardapioId}/items/{id}/disponivel")
    public void updateDisponibilidade(@PathVariable Long cardapioId, @PathVariable Long id) {
         cardapioService.indisponibilizarItem(cardapioId, id);
    }
    @PutMapping("/{cardapioId}/items/{id}/indisponivel")
    public void updateIndisponibilidade(@PathVariable Long cardapioId, @PathVariable Long id) {
         cardapioService.indisponibilizarItem(cardapioId, id);
    }
}
