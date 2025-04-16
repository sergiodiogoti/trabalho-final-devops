package com.ms.cartoes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    @NotBlank(message = "Nome é Obrigatorio")
    private String nome;
    @NotBlank(message = "Email é Obrigatorio")
    private String email;
}
