package com.uninter.raiazesdonordeste.pedido.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.ItemPedidoPostDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.ItemPedidoUpdateDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.PedidoPostDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.PedidoUpdateDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.ItemPedidoMaxDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.ItemPedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoMaxDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;
import com.uninter.raiazesdonordeste.pedido.domain.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResumoDto>> findAll(@RequestParam(required = false) Long unidadeId, @RequestParam(required = false) Long clienteId) {
        return ResponseEntity.ok(pedidoService.findAll(unidadeId, clienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoMaxDto> save(@RequestBody @Valid PedidoPostDto request) {
        PedidoMaxDto response = pedidoService.save(request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoMaxDto> update(@PathVariable Long id, @RequestBody @Valid PedidoUpdateDto request) {
        return ResponseEntity.ok(pedidoService.update(id, request));
    }


    @PatchMapping("/{id}/status/aguardar-pagamento")
    public ResponseEntity<PedidoMaxDto> finalizarCompra(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.aguardarPagamento(id));
    }

    @PatchMapping("/{id}/status/iniciar-preparo")
    public ResponseEntity<PedidoMaxDto> iniciarPreparo(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.iniciarPreparo(id));
    }

    @PatchMapping("/{id}/status/pronto")
    public ResponseEntity<PedidoMaxDto> pronto(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.pronto(id));
    }

    @PatchMapping("/{id}/status/entregar")
    public ResponseEntity<PedidoMaxDto> entregar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.entregar(id));
    }

    @PatchMapping("/{id}/status/cancelar")
    public ResponseEntity<PedidoMaxDto> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.cancelar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pedidoId}/items")
    public ResponseEntity<List<ItemPedidoResumoDto>> findItems(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pedidoService.findItems(pedidoId));
    }

    @GetMapping("/{pedidoId}/items/{itemPedidoId}")
    public ResponseEntity<ItemPedidoMaxDto> findItemById(@PathVariable Long pedidoId, @PathVariable Long itemPedidoId) {
        return ResponseEntity.ok(pedidoService.findItemById(pedidoId, itemPedidoId));
    }

    @PostMapping("/{pedidoId}/items")
    public ResponseEntity<ItemPedidoMaxDto> saveItem(@PathVariable Long pedidoId, @RequestBody @Valid ItemPedidoPostDto request) {
        ItemPedidoMaxDto response = pedidoService.saveItem(pedidoId, request);
        var uri = UriBuilder.create(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{pedidoId}/items/{itemPedidoId}")
    public ResponseEntity<ItemPedidoMaxDto> updateItem(@PathVariable Long pedidoId, @PathVariable Long itemPedidoId, @RequestBody @Valid ItemPedidoUpdateDto request) {
        return ResponseEntity.ok(pedidoService.updateItem(pedidoId, itemPedidoId, request));
    }

    @DeleteMapping("/{pedidoId}/items/{itemPedidoId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long pedidoId, @PathVariable Long itemPedidoId) {
        pedidoService.deleteItem(pedidoId, itemPedidoId);
        return ResponseEntity.noContent().build();
    }
}
