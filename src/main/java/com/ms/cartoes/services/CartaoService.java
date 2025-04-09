package com.ms.cartoes.services;

import com.ms.cartoes.entities.Cartao;
import com.ms.cartoes.entities.Proposta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CartaoService {
    Optional<Cartao> buscarPorID(Long id);

    List<Cartao> buscarTodosCartaos();

    void emitirCartao(Proposta proposta);

}