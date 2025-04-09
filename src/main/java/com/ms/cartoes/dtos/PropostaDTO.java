package com.ms.cartoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropostaDTO {

    @NotBlank(message = "O campo nome da proposta não pode estar em branco")
    @NotNull(message = "O campo nome da proposta não pode ser null")
    private String nomeProposta;

    @NotNull(message = "O campo limite da proposta não pode ser null")
    private BigDecimal limiteProposta;

    @NotNull(message = "O campo cliente não pode ser null")
    private Long cliente;

    private String status;

}
