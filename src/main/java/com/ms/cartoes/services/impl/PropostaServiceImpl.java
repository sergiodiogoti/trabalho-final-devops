package com.ms.cartoes.services.impl;

import com.ms.cartoes.dtos.PropostaResponse;
import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.enums.StatusProposta;
import com.ms.cartoes.exception.PropostaException;
import com.ms.cartoes.repositories.PropostaRepository;
import com.ms.cartoes.services.AnaliseLimiteCreditoService;
import com.ms.cartoes.services.CartaoService;
import com.ms.cartoes.services.ClienteService;
import com.ms.cartoes.services.PropostaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaServiceImpl implements PropostaService {

    final PropostaRepository propostaRepository;
    final ClienteService clienteService;
    final AnaliseLimiteCreditoService analiseLimiteCreditoService;
    final CartaoService cartaoService;

    public PropostaServiceImpl(PropostaRepository propostaRepository, ClienteService clienteService, AnaliseLimiteCreditoService analiseLimiteCreditoService, CartaoService cartaoService) {
        this.propostaRepository = propostaRepository;
        this.clienteService = clienteService;
        this.analiseLimiteCreditoService = analiseLimiteCreditoService;
        this.cartaoService = cartaoService;
    }
    @Override
    @Transactional
    public Optional<Proposta> buscarPorID(Long id) {
        return propostaRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Proposta> buscarPropostaPorClienteEStatus(Cliente cliente, StatusProposta status) {
        return propostaRepository.findByClienteAndStatus(cliente, status);
    }
    @Override
    @Transactional
    public List<Proposta> buscarTodasPropostas() {
        return propostaRepository.findAll();
    }

    @Override
    @Transactional
    public PropostaResponse criarProposta(Long idCliente, Proposta proposta) {

        Optional<Cliente> cliente = clienteService.buscarPorID(idCliente);
        if(!cliente.isPresent()){
            throw new PropostaException("Não foi possivel criar proposta, Cliente não esta cadastrado!");
        }

        Optional<Proposta> propostaStatus =  buscarPropostaPorClienteEStatus(cliente.get(), StatusProposta.APROVADA);
        if(propostaStatus.isPresent()){
            throw new PropostaException("Não foi possivel criar uma nova proposta, Cliente já tem uma proposta Aprovada!");
        }
        proposta.setCliente(cliente.get());

        analiseLimiteCreditoService.analisar(proposta);

        proposta.setDataHoraRegistro(LocalDateTime.now());
        propostaRepository.save(proposta);

        if(proposta != null && proposta.getId() != null && proposta.getStatus() != null) {
            if (proposta.getStatus().equals(StatusProposta.RECUSADA)) {
                throw new PropostaException("Sua proposta foi recusada devido a não atender os critérios de crédito.");
            }
            if (proposta.getStatus().equals(StatusProposta.APROVADA)) {
                cartaoService.emitirCartao(proposta);
                return new PropostaResponse("Sua proposta foi aprovada, seu cartão esta sendo emitido em breve chegará até você.");
            }
        }
        return null;
    }
}
