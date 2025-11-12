package com.br.pdvpostocombustivel.api.custo.dto;

import com.br.pdvpostocombustivel.enums.TipoCusto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CustoRequest(
        double imposto,
        double custoVariavel,
        double custoFixo,
        double margemLucro,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataProcessamento,
        TipoCusto tipoCusto
) {
}
