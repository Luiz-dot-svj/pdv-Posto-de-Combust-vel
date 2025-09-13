package com.br.pdvpostodecombustivel.domain.entity;

public class Produto {

    //atributos
    private String nome;
    private String referencia;
    private String fornecedor;
    private String marca;
    private String categoria;

    //construtor
    public Produto(String nome, String referencia, String fornecedor, String marca, String categoria) {
        this.nome = nome;
        this.referencia = referencia;
        this.fornecedor = fornecedor;
        this.marca = marca;
        this.categoria = categoria;
    }

    //getters
    public String getCategoria() {
        return categoria;
    }
    public String getMarca() {
        return marca;
    }
    public String getFornecedor() {
        return fornecedor;
    }
    public String getNome() {
        return nome;
    }
    public String getReferencia() {
        return referencia;
    }

    //setters
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}