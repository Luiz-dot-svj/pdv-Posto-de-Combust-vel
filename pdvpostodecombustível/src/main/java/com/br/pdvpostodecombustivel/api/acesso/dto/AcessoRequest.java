package com.br.pdvpostodecombustivel.api.acesso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AcessoRequest(
        @NotBlank @Size(max = 30) String usuario,
        @NotBlank @Size(max = 15) String senha
) {}
