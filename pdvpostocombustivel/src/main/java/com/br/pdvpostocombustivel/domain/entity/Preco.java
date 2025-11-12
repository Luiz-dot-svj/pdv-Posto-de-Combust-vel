package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "preco")
public class Preco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private BigDecimal valor;

    @Column(length = 10, nullable = false)
    private LocalDate dataAlteracao;

    @Column(length = 10, nullable = false)
    private LocalDate horaAlteracao;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public Preco() {
    }
    
    public Preco(BigDecimal valor, LocalDate dataAlteracao, LocalDate horaAlteracao, Produto produto) {
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
        this.valor = valor;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }
    public LocalDate getHoraAlteracao() {
        return horaAlteracao;
    }
    public Produto getProduto() {
        return produto;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    public void setHoraAlteracao(LocalDate horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
