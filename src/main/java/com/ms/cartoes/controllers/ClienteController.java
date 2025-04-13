package com.ms.cartoes.controllers;

import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.services.ClienteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable String id) {
        return clienteService.buscarPorId(id);
    }
}


