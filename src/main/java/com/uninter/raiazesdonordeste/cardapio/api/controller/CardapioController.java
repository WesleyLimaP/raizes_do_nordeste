package com.uninter.raiazesdonordeste.cardapio.api.controller;

import com.uninter.raiazesdonordeste.cardapio.domain.model.Cardapio;
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
    @PutMapping
    public Cardapio update(@PathVariable Long id, @RequestBody Cardapio cardapio) {
        return cardapioService.update(id, cardapio);
    }
}
