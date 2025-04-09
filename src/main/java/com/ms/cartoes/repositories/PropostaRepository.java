package com.ms.cartoes.repositories;

import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.enums.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByClienteAndStatus(Cliente cliente, StatusProposta status);
}

