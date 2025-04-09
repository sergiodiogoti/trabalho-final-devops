package com.ms.cartoes.services;

import com.ms.cartoes.entities.Proposta;

public interface RegraLimiteCredito {
    void aplicar(Proposta proposta);
}
