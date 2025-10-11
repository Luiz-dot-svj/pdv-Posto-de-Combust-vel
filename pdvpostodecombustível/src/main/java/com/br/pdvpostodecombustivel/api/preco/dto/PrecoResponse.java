package com.br.pdvpostodecombustivel.api.preco.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record PrecoResponse(
        Long id,
        BigDecimal valor,
        LocalDate dataAlteracao,
        LocalTime horaAlteracao
) {}
