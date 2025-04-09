package com.ms.cartoes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String numero;

    @NotBlank
    @NotNull
    private String conta;

    private BigDecimal limite;

    @ManyToOne
    private Proposta proposta;

    @NotBlank
    @NotNull
    private String validade;

    @NotNull
    private Integer ccv;

    @NotNull
    private LocalDateTime dataHoraRegistro;

}

