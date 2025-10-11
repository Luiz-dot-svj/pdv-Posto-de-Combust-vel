package com.br.pdvpostodecombustivel.api.preco.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record PrecoRequest(
        @NotNull BigDecimal valor,
        @NotNull LocalDate dataAlteracao,
        @NotNull LocalTime horaAlteracao
) {}
