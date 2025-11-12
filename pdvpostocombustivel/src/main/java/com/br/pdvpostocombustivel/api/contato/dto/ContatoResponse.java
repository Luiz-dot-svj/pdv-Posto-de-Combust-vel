package com.br.pdvpostocombustivel.api.contato.dto;

import com.br.pdvpostocombustivel.enums.TipoContato;

public record ContatoResponse(
        Long id,
        String telefone,
        String email,
        String endereco,
        TipoContato tipoContato,
        Long pessoaId
) {

}
