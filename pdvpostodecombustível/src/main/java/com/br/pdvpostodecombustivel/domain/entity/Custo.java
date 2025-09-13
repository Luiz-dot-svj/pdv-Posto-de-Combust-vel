package com.br.pdvpostodecombustivel.domain.entity;
import java.util.Date;

public class Custo {

    //atributos
    private Double imposto;
    private Double custoVariavel;
    private Double custoFixo;
    private Double margemLucro;
    private Date dataProcessamento;

    //construtor
    public Custo(Double imposto, Double custoVariavel, Double custoFixo, Double margemLucro, Date dataProcessamento) {
        this.imposto = imposto;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }

    //getters
    public Double getCustoFixo() {
        return custoFixo;
    }
    public Date getDataProcessamento() {
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

    //setters
    public void setCustoFixo(Double custoFixo) {

    }
    public void setCustoVariavel(Double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }
    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
    public void setImposto(Double imposto) {
        this.imposto = imposto;
    }
    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }
}