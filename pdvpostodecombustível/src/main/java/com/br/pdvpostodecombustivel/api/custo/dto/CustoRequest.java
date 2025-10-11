package com.br.pdvpostodecombustivel.api.custo.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record CustoRequest(
        @NotNull Double imposto,
        @NotNull Double custoVariavel,
        @NotNull Double custoFixo,
        @NotNull Double margemLucro,
        @NotNull Date dataProcessamento
) {}
