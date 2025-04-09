package com.ms.cartoes.controllers;

import com.ms.cartoes.dtos.PropostaDTO;
import com.ms.cartoes.dtos.PropostaResponse;
import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.exception.PropostaException;
import com.ms.cartoes.services.ClienteService;
import com.ms.cartoes.services.PropostaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/propostas")
@RestController
public class PropostaController {
    @Autowired
    private PropostaService propostaService;

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Criar Proposta")
    public ResponseEntity<String> criarProposta(@RequestBody @Valid PropostaDTO propostaDTO) {
        try {
            PropostaResponse propostaResponse = propostaService.criarProposta(propostaDTO.getCliente(), modelMapper.map(propostaDTO, Proposta.class));
            return new ResponseEntity(propostaResponse, HttpStatus.CREATED);
        }
        catch (PropostaException e) {
            PropostaResponse propostaResponse =  new PropostaResponse(e.getMessage());
            return new ResponseEntity(propostaResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    @Operation(summary = "Buscar todas propostas")
    public ResponseEntity<List<Proposta>> buscarTodasPropostas() {
        return ResponseEntity.ok(propostaService.buscarTodasPropostas());
    }
}
