
package com.br.pdvpostodecombustivel.api.contato.dto;

// Para Entrada
public record ContatoRequest(
        String telefone,
        String email,
        String endereco
)
{ }
