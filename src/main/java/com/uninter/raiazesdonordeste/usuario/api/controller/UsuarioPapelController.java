package com.uninter.raiazesdonordeste.usuario.api.controller;
import com.uninter.raiazesdonordeste.usuario.api.model.dto.request.UsuarioPapelPostDto;
import com.uninter.raiazesdonordeste.usuario.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-papeis")
public class UsuarioPapelController {
    @Autowired
    private UsuarioService usuarioService;



    @PostMapping
    public ResponseEntity<Void> associarPapel(@RequestBody @Valid UsuarioPapelPostDto request) {
       usuarioService.associarPapel(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.desassociarPapel(id);
        return ResponseEntity.noContent().build();
    }
}
