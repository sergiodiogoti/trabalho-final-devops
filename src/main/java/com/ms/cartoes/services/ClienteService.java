package com.ms.cartoes.services;

import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.repository.ClienteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setId(UUID.randomUUID().toString());
        return clienteRepository.save(cliente); // Salva no Redis
    }

    @Cacheable(value = "clientes", key = "#id")
    public Cliente buscarPorId(String id) {
        return clienteRepository.findById(id).orElse(null); // Recupera do Redis
    }
}

