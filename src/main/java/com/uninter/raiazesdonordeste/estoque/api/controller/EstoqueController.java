package com.uninter.raiazesdonordeste.estoque.api.controller;

import com.uninter.raiazesdonordeste.estoque.api.model.dto.EstoqueMovimentacaoDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueMinDto;
import com.uninter.raiazesdonordeste.estoque.domain.model.Estoque;
import com.uninter.raiazesdonordeste.estoque.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;


    @GetMapping
    public ResponseEntity<List<EstoqueMinDto>> findAll() {
        List<EstoqueMinDto> estoques = estoqueService.findAll();
        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDto> findById(@PathVariable Long id) {
        var estoqueDto = estoqueService.findById(id);
        return ResponseEntity.ok(estoqueDto);
    }

    @GetMapping("/unidades/{unidadeId}/produtos/{produtoId}")
    public ResponseEntity<EstoqueDto> findByUnidadeAndProduto(@PathVariable Long unidadeId, @PathVariable Long produtoId) {
        var estoqueDto = estoqueService.findById(unidadeId, produtoId);
        return ResponseEntity.ok(estoqueDto);
    }
    @GetMapping("/unidades/{unidadeId}/produtos")
    public ResponseEntity<List<EstoqueMinDto>> findAllByUnidade(@PathVariable Long unidadeId) {
        var estoqueDto = estoqueService.findAllByUnidade(unidadeId);
        return ResponseEntity.ok(estoqueDto);
    }


    @PutMapping("/unidades/{unidadeId}/produtos/{produtoId}/entrada")
    public ResponseEntity<EstoqueDto> entrada(@PathVariable Long unidadeId, @PathVariable Long produtoId, @RequestBody EstoqueMovimentacaoDto estoque) {
        var response = estoqueService.updateQuantidadeAtualEntrada(unidadeId, produtoId, estoque);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/entrada")
    public ResponseEntity<EstoqueDto> entrada(@PathVariable Long id, @RequestBody EstoqueMovimentacaoDto estoque) {
        var response = estoqueService.updateQuantidadeAtualEntrada(id, estoque);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/unidades/{unidadeId}/produtos/{produtoId}/saida")
    public ResponseEntity<EstoqueDto> saida(@PathVariable Long unidadeId, @PathVariable Long produtoId, @RequestBody EstoqueMovimentacaoDto estoque) {
        var response = estoqueService.updateQuantidadeAtualSaida(unidadeId, produtoId, estoque);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/saida")
    public ResponseEntity<EstoqueDto> saida(@PathVariable Long id, @RequestBody EstoqueMovimentacaoDto estoque) {
        var response = estoqueService.updateQuantidadeAtualSaida(id, estoque);
        return ResponseEntity.ok(response);
    }


}
