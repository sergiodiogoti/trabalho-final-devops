package com.ms.cartoes.services;

import com.ms.cartoes.dtos.PropostaResponse;
import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.enums.StatusProposta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PropostaService {
    Optional<Proposta> buscarPorID(Long id);
    Optional<Proposta> buscarPropostaPorClienteEStatus(Cliente cliente, StatusProposta status);

    List<Proposta> buscarTodasPropostas();

    PropostaResponse criarProposta(Long idCliente, Proposta proposta);

}