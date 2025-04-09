package com.ms.cartoes.repositories;

import com.ms.cartoes.entities.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}

