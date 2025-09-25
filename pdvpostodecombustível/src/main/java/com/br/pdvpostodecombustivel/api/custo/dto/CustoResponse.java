
package com.br.pdvpostodecombustivel.api.custo.dto;

import java.time.LocalDate;


public record CustoResponse(
        Double imposto,
        Double custoVariavel,
        Double custoFixo,
        Double margemLucro,
        LocalDate dataProcessamento
) {
}
