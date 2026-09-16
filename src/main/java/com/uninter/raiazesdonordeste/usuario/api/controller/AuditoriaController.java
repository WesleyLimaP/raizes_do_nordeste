package com.uninter.raiazesdonordeste.usuario.api.controller;

import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaMaxDto;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.auditoria.AuditoriaResumoDto;
import com.uninter.raiazesdonordeste.usuario.domain.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaResumoDto>> findAll() {
        return ResponseEntity.ok(auditoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaMaxDto> findById(@PathVariable Long id) {
        var auditoriaDto = auditoriaService.findById(id);
        return ResponseEntity.ok(auditoriaDto);
    }

    /* TODO
CRIAR EVENTO DE AUDITORIA E LANÇAR NOS SEGUINTES METODOS
Pedido criado / status alterado	✅ Sim	Disputa de cliente, rastreabilidade operacional
Pagamento aprovado/pendente/falhou	✅ Sim	Dinheiro envolvido — sempre auditar
Usuário criado / papel alterado	✅ Sim	Segurança — quem ganhou acesso e quando
Consentimento LGPD revogado/concedido	✅ Sim	Exigência legal explícita
     */

}
