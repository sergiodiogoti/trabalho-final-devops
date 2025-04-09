package com.ms.cartoes.entities;

import com.ms.cartoes.enums.StatusProposta;
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
@Table(name = "propostas")
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    private BigDecimal limite;

    @ManyToOne
    private Cliente cliente;

    @NotNull
    @Enumerated(value= EnumType.STRING)
    private StatusProposta status;

    @NotNull
    private LocalDateTime dataHoraRegistro;

}
