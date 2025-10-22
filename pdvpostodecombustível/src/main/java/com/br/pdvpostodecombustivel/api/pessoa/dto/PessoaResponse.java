package com.br.pdvpostodecombustivel.api.pessoa.dto;

import java.time.LocalDate;

public record PessoaResponse(
        Long id, // Adicionado o ID
        String nomeCompleto,
        String cpfCnpj,
        Long numeroCtps,
        LocalDate dataNascimento
) {}
