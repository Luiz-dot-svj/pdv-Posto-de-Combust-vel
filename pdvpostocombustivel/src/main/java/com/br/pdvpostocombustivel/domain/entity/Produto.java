package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoProduto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 500, nullable = false)
    private String referencia;

    @Column(length = 100, nullable = false)
    private String fornecedor;

    @Column(length = 30, nullable = false)
    private String marca;

    @Column(length = 30, nullable = false)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProduto tipoProduto;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estoque> estoques = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preco> precos = new ArrayList<>();

    public Produto() {
    }
    
    public Produto(String nome, String referencia, String fornecedor, String marca, String categoria, TipoProduto tipoProduto) {
        this.nome = nome;
        this.referencia = referencia;
        this.fornecedor = fornecedor;
        this.marca = marca;
        this.categoria = categoria;
        this.tipoProduto = tipoProduto;
    }

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
    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }
    public List<Estoque> getEstoques() {
        return estoques;
    }
    public List<Preco> getPrecos() {
        return precos;
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
    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }
    public void setPrecos(List<Preco> precos) {
        this.precos = precos;
    }
}
