package com.ms.cartoes.services.impl;

import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.exception.ClienteException;
import com.ms.cartoes.repositories.ClienteRepository;
import com.ms.cartoes.services.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    @Transactional
    public Optional<Cliente> buscarPorID(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        Optional<Cliente> existeEmail = buscarClientePorEmail(cliente.getEmail());
        if(existeEmail.isPresent()){
            throw new ClienteException("Já existe um Cliente cadastrado com esse email");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Cliente> buscarClientePorEmail(String email ) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Cliente atualizarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente.getId());
        return clienteRepository.save(cliente);}

    @Override
    @Transactional
    public void deletarClientePorID(Long id) {

    }
}
