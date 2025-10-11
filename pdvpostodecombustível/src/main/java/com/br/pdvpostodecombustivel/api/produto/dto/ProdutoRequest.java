package com.br.pdvpostodecombustivel.api.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(
        @NotBlank @Size(max = 20) String nome,
        @NotBlank @Size(max = 500) String referencia,
        @NotBlank @Size(max = 100) String fornecedor,
        @NotBlank @Size(max = 30) String marca,
        @NotBlank @Size(max = 30) String categoria
) {}
