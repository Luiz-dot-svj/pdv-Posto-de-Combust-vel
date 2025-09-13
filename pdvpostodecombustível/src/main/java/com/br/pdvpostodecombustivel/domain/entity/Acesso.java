package com.br.pdvpostodecombustivel.domain.entity;



public class Acesso {

    private String usuario;
    private String senha;

    //construtor
    public Acesso(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    //getters

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    //setters
    public void setUsuario(){
        this.usuario = usuario;
    }

    public void setSenha(){
        this.senha = senha;
    }
}