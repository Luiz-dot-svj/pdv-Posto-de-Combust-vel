
package com.br.pdvpostodecombustivel.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(length = 500, nullable = false)
    private String referencia;

    @Column(length = 100, nullable = false)
    private String fornecedor;

    @Column(length = 30, nullable = false)
    private String marca;

    @Column(length = 30, nullable = false)
    private String categoria;

    /** Construtor JPA */
    protected Produto() {}

    //construtor
    public Produto(String nome, String referencia, String fornecedor, String marca, String categoria) {
        this.nome = nome;
        this.referencia = referencia;
        this.fornecedor = fornecedor;
        this.marca = marca;
        this.categoria = categoria;
    }

    //getters
    public Long getId() {
        return id;
    }

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
    public void setId(Long id) {
        this.id = id;
    }

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
