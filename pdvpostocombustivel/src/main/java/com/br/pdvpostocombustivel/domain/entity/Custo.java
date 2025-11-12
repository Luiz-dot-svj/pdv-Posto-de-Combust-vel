package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoCusto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "custo")
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, nullable = false)
    private Double imposto;

    @Column(length = 10, nullable = false)
    private Double custoVariavel;

    @Column(length = 10, nullable = false)
    private Double custoFixo;

    @Column(length = 5, nullable = false)
    private Double margemLucro;

    @Column(length = 10, nullable = false)
    private LocalDate dataProcessamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_custo", nullable = false, length = 30)
    private TipoCusto tipoCusto;
    public Custo() {
    }
    
    public Custo(Double imposto, Double custoVariavel, Double custoFixo, Double margemLucro, LocalDate dataProcessamento, TipoCusto tipoCusto) {
        this.imposto = imposto;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
        this.tipoCusto = tipoCusto;
    }

    public Long getId() {
        return id;
    }
    
    public Double getCustoFixo() {
        return custoFixo;
    }
    public LocalDate getDataProcessamento() {
        return dataProcessamento;
    }
    public Double getCustoVariavel() {
        return custoVariavel;
    }
    public Double getImposto() {
        return imposto;
    }
    public Double getMargemLucro() {
        return margemLucro;
    }
    public TipoCusto getTipoCusto() {
        return tipoCusto;
    }

    public void setCustoFixo(Double custoFixo) {
        this.custoFixo = custoFixo;
    }
    public void setCustoVariavel(Double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }
    public void setDataProcessamento(LocalDate dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
    public void setImposto(Double imposto) {
        this.imposto = imposto;
    }
    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }
    public void setTipoCusto(TipoCusto tipoCusto) {
        this.tipoCusto = tipoCusto;
    }
}
