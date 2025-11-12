package com.br.pdvpostocombustivel.api.preco.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrecoResponse(
        Long id,
        LocalDate dataAlteracao,
        LocalDate horaAlteracao,
        BigDecimal valor,
        Long produtoId
) {
}
