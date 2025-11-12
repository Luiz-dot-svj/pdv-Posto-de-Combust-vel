package com.br.pdvpostocombustivel.enums;

public enum TipoContato {
    CLIENTE("Contato Cliente"),
    FORNECEDOR("Contato Fornecedor"),
    FUNCIONARIO("Contato Funcionário");

    private final String descricao;

    private TipoContato(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
