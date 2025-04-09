package com.ms.cartoes.controllers;

import com.ms.cartoes.entities.Cartao;
import com.ms.cartoes.services.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cartoes")
@RestController
public class CartaoController {
    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    @Operation(summary = "Buscar todos cartões")
    public ResponseEntity<List<Cartao>> buscarTodasCartaos() {
        return ResponseEntity.ok(cartaoService.buscarTodosCartaos());
    }
}
