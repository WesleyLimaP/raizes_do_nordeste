package com.uninter.raiazesdonordeste.estoque.api.controller;

import com.uninter.raiazesdonordeste.estoque.api.model.dto.EstoqueMovimentacaoDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueMaxDto;
import com.uninter.raiazesdonordeste.estoque.api.model.dto.response.EstoqueResumoDto;
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
    public ResponseEntity<List<EstoqueResumoDto>> findAll() {
        List<EstoqueResumoDto> estoques = estoqueService.findAll();
        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueMaxDto> findById(@PathVariable Long id) {
        var estoqueDto = estoqueService.findById(id);
        return ResponseEntity.ok(estoqueDto);
    }

    @GetMapping("/unidades/{unidadeId}/produtos/{produtoId}")
    public ResponseEntity<EstoqueMaxDto> findByUnidadeAndProduto(@PathVariable Long unidadeId, @PathVariable Long produtoId) {
        var estoqueDto = estoqueService.findById(unidadeId, produtoId);
        return ResponseEntity.ok(estoqueDto);
    }
    @GetMapping("/unidades/{unidadeId}/produtos")
    public ResponseEntity<List<EstoqueResumoDto>> findAllByUnidade(@PathVariable Long unidadeId) {
        var estoqueDto = estoqueService.findAllByUnidade(unidadeId);
        return ResponseEntity.ok(estoqueDto);
    }


    @PostMapping("/unidades/{unidadeId}/produtos/{produtoId}/movimentacoes/entrada")
    public ResponseEntity<EstoqueMaxDto> entrada(@PathVariable Long unidadeId, @PathVariable Long produtoId, @RequestBody EstoqueMovimentacaoDto estoque) {
        var response = estoqueService.updateQuantidadeAtualEntrada(unidadeId, produtoId, estoque);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/unidades/{unidadeId}/produtos/{produtoId}/movimentacoes/saida")
    public ResponseEntity<EstoqueMaxDto> saida(@PathVariable Long unidadeId, @PathVariable Long produtoId, @RequestBody EstoqueMovimentacaoDto estoque) {
        var response = estoqueService.updateQuantidadeAtualSaida(unidadeId, produtoId, estoque);
        return ResponseEntity.ok(response);
    }
}
