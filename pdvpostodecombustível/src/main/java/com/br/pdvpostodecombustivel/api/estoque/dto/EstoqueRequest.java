package com.br.pdvpostodecombustivel.api.estoque.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EstoqueRequest(
        @NotNull @PositiveOrZero BigDecimal quantidade,
        @NotBlank String localTanque,
        @NotBlank String localEndereco,
        @NotBlank String loteFabricacao,
        @NotNull @FutureOrPresent LocalDate dataValidade
) {}
