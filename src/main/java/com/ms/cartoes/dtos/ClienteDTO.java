package com.ms.cartoes.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClienteDTO {

    @NotBlank(message = "O campo nome não pode estar em branco")
    @NotNull(message = "O campo nome não pode ser null")
    private String nome;

    @NotBlank(message = "O campo endereco não pode estar em branco")
    @NotNull(message = "O campo endereco não pode ser null")
    private String endereco;

    @NotBlank(message = "O campo endereco não pode estar em branco")
    @NotNull(message = "O campo endereco não pode ser null")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    private String status;
    private BigDecimal rendaMensal;


}
