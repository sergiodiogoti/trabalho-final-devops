package com.ms.cartoes.controllers;

import com.ms.cartoes.dtos.ClienteDTO;
import com.ms.cartoes.dtos.ClienteResponse;
import com.ms.cartoes.dtos.PropostaResponse;
import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.enums.StatusCliente;
import com.ms.cartoes.exception.ClienteException;
import com.ms.cartoes.exception.PropostaException;
import com.ms.cartoes.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/clientes")
@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Cadastrar cliente")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        try {
            Cliente cliente = new Cliente();
            modelMapper.map(clienteDTO, cliente);
            cliente.setStatus(StatusCliente.ATIVO);
            Cliente clienteSalvo = clienteService.salvarCliente(cliente);
            return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
        }
        catch (ClienteException e) {
            ClienteResponse clienteResponse =  new ClienteResponse(e.getMessage());
            return new ResponseEntity(clienteResponse, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping
    @Operation(summary = "Buscar todos clientes")
    public ResponseEntity<List<Cliente>> buscarTodosClientes() {
        List<Cliente> clientes = clienteService.buscarTodosClientes();
        return ResponseEntity.ok(clientes);
    }
}
