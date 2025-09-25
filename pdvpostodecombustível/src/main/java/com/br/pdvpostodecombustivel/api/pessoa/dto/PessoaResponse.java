
package com.br.pdvpostodecombustivel.api.pessoa.dto;

import com.br.pdvpostodecombustivel.enums.TipoPessoa;

import java.time.LocalDate;

// Para resposta
public record PessoaResponse(
        String nomeCompleto,
        String cpfCnpj,
        Long numeroCtps,
        LocalDate dataNascimento
){}
