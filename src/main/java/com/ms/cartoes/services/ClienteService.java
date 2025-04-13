package com.ms.cartoes.services;

import com.ms.cartoes.entities.Cliente;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ClienteService {

    private final RedisTemplate<String, Cliente> redisTemplate;

    public ClienteService(RedisTemplate<String, Cliente> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setId(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(cliente.getId(), cliente);
        return cliente;
    }

    public Cliente buscarPorId(String id) {
        return redisTemplate.opsForValue().get(id);
    }
}
