package com.br.pdvpostocombustivel.api.produto.dto;

import com.br.pdvpostocombustivel.api.preco.dto.PrecoResponse;
import com.br.pdvpostocombustivel.enums.TipoProduto;

import java.util.List;

public record ProdutoResponse(
        Long id,
        String nome,
        String referencia,
        String fornecedor,
        String marca,
        String categoria,
        TipoProduto tipoProduto,
        List<PrecoResponse> precos
) {
}
