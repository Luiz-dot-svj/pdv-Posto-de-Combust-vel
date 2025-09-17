package com.br.pdvpostodecombustivel.api.pessoa.dto;
import java.time.LocalDate;

// Para Resposta
public record PessoaResponse(
        Long id,
        String nomeCompleto,
        String cpfCnpj,
        Long numeroCtps,
        LocalDate dataNascimento
) {}