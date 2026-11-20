package com.uninter.raiazesdonordeste.cliente.api.controller;

import com.uninter.raiazesdonordeste.cardapio.api.Util.UriBuilder;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.request.ClientePostDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.request.ClienteUpdateDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.response.ClienteMaxDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.response.ClienteResumoDto;
import com.uninter.raiazesdonordeste.cliente.domain.service.ClienteService;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResumoDto>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteMaxDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteMaxDto> save(@RequestBody ClientePostDto request) {
        ClienteMaxDto response = clienteService.save(request);
        return ResponseEntity.created(UriBuilder.create(response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteMaxDto> update(@PathVariable Long id, @RequestBody ClienteUpdateDto request) {
        return ResponseEntity.ok(clienteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clienteId}/pedidos")
    public ResponseEntity<List<PedidoResumoDto>> findPedidos(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.findPedidos(clienteId));
    }

    @GetMapping("/{clienteId}/conta-fidelidade")
    public ResponseEntity<ContaFidelidadeMaxDto> findContaFidelidade(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.findContaFidelidade(clienteId));
    }

    @GetMapping("/{clienteId}/consentimentos-lgpd")
    public ResponseEntity<List<ConsentimentoLgpdResumoDto>> findConsentimentosLgpd(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.findConsentimentosLgpd(clienteId));
    }

    @GetMapping("/{clienteId}/campanhas-fidelidade")
    public ResponseEntity<List<CampanhaFidelidadeResumoDto>> findCampanhasFidelidade(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.findCampanhasFidelidade(clienteId));
    }
}
