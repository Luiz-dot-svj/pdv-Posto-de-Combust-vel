package com.br.pdvpostodecombustivel.api.preco.dto;

import com.br.pdvpostodecombustivel.config.LocalTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record PrecoRequest(
        @NotNull BigDecimal valor,
        @NotNull LocalDate dataAlteracao,

        @NotNull
        @JsonDeserialize(using = LocalTimeDeserializer.class)
        LocalTime horaAlteracao
) {}
