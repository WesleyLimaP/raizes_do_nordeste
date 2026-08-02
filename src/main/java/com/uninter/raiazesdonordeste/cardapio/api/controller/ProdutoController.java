package com.uninter.raiazesdonordeste.cardapio.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoMaxDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoPostDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoResumoDto;
import com.uninter.raiazesdonordeste.cardapio.api.model.dto.produto.ProdutoUpdateDto;
import com.uninter.raiazesdonordeste.cardapio.domain.model.Produto;
import com.uninter.raiazesdonordeste.cardapio.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public ResponseEntity<List<ProdutoResumoDto>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoMaxDto> findById(@PathVariable Long id) {
        ProdutoMaxDto produtoDto = produtoService.findById(id);
        return ResponseEntity.ok().body(produtoDto);
    }
    @PostMapping
    public ResponseEntity<ProdutoMaxDto> save(@RequestBody ProdutoPostDto produtoPostDto) {
        ProdutoMaxDto produtoMaxDto = produtoService.save(produtoPostDto);
        return ResponseEntity.ok(produtoMaxDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoMaxDto> update(@PathVariable Long id, @RequestBody ProdutoUpdateDto produtoUpdateDto) {
        ProdutoMaxDto produtoMaxDto = produtoService.update(id, produtoUpdateDto);
        return ResponseEntity.ok(produtoMaxDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
