package com.ms.cartoes.repository;

import com.ms.cartoes.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, String> {

}

