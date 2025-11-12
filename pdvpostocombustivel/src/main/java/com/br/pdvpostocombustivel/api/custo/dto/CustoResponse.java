package com.br.pdvpostocombustivel.api.custo.dto;

import com.br.pdvpostocombustivel.enums.TipoCusto;

import java.time.LocalDate;


public record CustoResponse(
        Long id,
        double imposto,
        double custoVariavel,
        double custoFixo,
        double margemLucro,
        LocalDate dataProcessamento,
        TipoCusto tipoCusto
) {
}
