
package com.br.pdvpostodecombustivel.api.produto.dto;

public record ProdutoRequest(
        String nome,
        String referencia,
        String fornecedor,
        String marca,
        String categoria
) {
}
