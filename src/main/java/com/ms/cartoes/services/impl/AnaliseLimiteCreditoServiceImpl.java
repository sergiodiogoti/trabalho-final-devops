package com.ms.cartoes.services.impl;

import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.services.AnaliseLimiteCreditoService;
import com.ms.cartoes.services.RegraLimiteCredito;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseLimiteCreditoServiceImpl implements AnaliseLimiteCreditoService {
    private final List<RegraLimiteCredito> regraLimiteCreditos;

    public AnaliseLimiteCreditoServiceImpl(List<RegraLimiteCredito> regraLimiteCreditos) {
        this.regraLimiteCreditos = regraLimiteCreditos;
    }
    @Override
    public void analisar(Proposta proposta) {
        for (RegraLimiteCredito regra : regraLimiteCreditos) {
            regra.aplicar(proposta);
        }
    }
}

