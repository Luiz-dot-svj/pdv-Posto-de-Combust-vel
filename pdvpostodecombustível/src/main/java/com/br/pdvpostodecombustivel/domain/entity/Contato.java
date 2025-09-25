
package com.br.pdvpostodecombustivel.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato {

    //atributos

    @Column(length = 14, nullable = false)
    private String telefone;

    @Column (length = 50, nullable = false)
    private String email;

    @Column (length = 100, nullable = false)
    private String endereco;


    //construtor
    public Contato(String telefone, String email, String endereco) {
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    //getters
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }

    //setters
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


}
