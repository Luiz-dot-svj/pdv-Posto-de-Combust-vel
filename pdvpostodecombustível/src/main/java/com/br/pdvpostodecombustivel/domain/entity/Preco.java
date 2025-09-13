package com.br.pdvpostodecombustivel.domain.entity;


import java.math.BigDecimal;
import java.util.Date;

public class Preco{

    private BigDecimal valor;
    private String dataAlteracao;
    private Date horaAlteracao;

    public Preco (BigDecimal valor, String dataAlteracao, Date horaAlteracao){

        this.valor = valor;
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
    }


    public BigDecimal getValor() {
        return valor;
    }

    public String getDataAlteracao(){
        return dataAlteracao;
    }

    public Date getHoraAlteracao(){
        return horaAlteracao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setHoraAlteracao(Date horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
}