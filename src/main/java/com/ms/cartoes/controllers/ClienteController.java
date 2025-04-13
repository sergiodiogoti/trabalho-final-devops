package com.ms.cartoes.controllers;

import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.salvar(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable String id) {
        Cliente cliente = service.buscarPorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }
}

