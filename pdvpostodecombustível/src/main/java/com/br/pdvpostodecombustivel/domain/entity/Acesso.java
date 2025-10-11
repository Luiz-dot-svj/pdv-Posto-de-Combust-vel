
package com.br.pdvpostodecombustivel.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //atributos

    @Column(length = 30, nullable = false)
    private String usuario;

    @Column(length = 15, nullable = false)
    private String senha;

    public Acesso() {
    }

    //contrutor
    public Acesso(String usuario, String senha) {
        this.usuario = usuario;
        this.senha =senha;
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }
    public String getUsuario() {
        return usuario;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
