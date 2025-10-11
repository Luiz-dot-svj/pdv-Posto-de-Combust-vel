package com.br.pdvpostodecombustivel.api.produto.dto;

public record ProdutoResponse(
        Long id,
        String nome,
        String referencia,
        String fornecedor,
        String marca,
        String categoria
) {}
