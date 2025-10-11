package com.br.pdvpostodecombustivel.api.contato.dto;

public record ContatoResponse(
        Long id,
        String telefone,
        String email,
        String endereco
) {}
