package com.ms.cartoes.controllers;

import com.ms.cartoes.dto.ClienteDTO;
import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/cartoes/clientes")
@RequiredArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ClienteDTO dto){
        try {
            Cliente entidade = new Cliente();
            entidade.setNome(dto.getNome());
            entidade.setEmail(dto.getEmail());
            entidade = clienteService.salvar(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity  getCliente(@PathVariable String id) {
        try {
            return new ResponseEntity(clienteService.buscarPorId(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity  getClienteAll() {
        try {
            return new ResponseEntity(clienteService.buscarTodos(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


