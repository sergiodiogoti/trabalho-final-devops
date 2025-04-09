package com.ms.cartoes.services.impl;

import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.enums.StatusProposta;
import com.ms.cartoes.services.RegraLimiteCredito;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LimiteCreditoAprovado implements RegraLimiteCredito {
    @Override
    public void aplicar(Proposta proposta) {

        //aqui estou simulando uma regra que aprova o limite de crédito solicitado pelo cliente.
        BigDecimal limiteSolicitado = proposta.getLimite();
        BigDecimal limiteMaximo =  proposta.getCliente().getRendaMensal();

        if (limiteSolicitado.compareTo(limiteMaximo) < 0) {
            proposta.setStatus(StatusProposta.APROVADA);
        }
    }
}
