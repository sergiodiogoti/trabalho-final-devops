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
        return new ResponseEntity(clienteService.salvar(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity  getCliente(@PathVariable String id) {
        return new ResponseEntity(clienteService.buscarPorId(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity  getClienteAll() {
        return new ResponseEntity(clienteService.buscarTodos(), HttpStatus.OK);
    }
}


