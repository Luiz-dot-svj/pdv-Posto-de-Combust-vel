package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.enums.TipoPessoa;

import java.time.LocalDate;

public record PessoaResponse(
        Long id,
        String nomeCompleto,
        String cpfCnpj,
        long numeroCtps,
        LocalDate dataNascimento,
        TipoPessoa tipoPessoa
){}
