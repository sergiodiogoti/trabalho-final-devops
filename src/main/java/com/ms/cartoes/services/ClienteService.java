package com.ms.cartoes.services;

import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente buscarPorId(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Cliente> buscarTodos() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}

