package com.ms.cartoes.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDTO {

    @NotBlank
    @NotNull
    private String numeroCartao;

    @NotNull
    private BigDecimal limite;

    @NotNull
    private String nomeCliente;

    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String validade;

    @NotNull
    private Integer ccv;
}
