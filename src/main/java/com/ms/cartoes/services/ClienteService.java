package com.ms.cartoes.services;

import com.ms.cartoes.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClienteService {
    Optional<Cliente> buscarPorID(Long id);
    Optional<Cliente> buscarClientePorEmail(String email);

    List<Cliente> buscarTodosClientes();

    Cliente salvarCliente(Cliente cliente);

    Cliente atualizarCliente(Cliente cliente);

    void deletarClientePorID(Long id);



}