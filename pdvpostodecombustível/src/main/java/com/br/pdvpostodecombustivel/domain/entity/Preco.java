
package com.br.pdvpostodecombustivel.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "preco")
public class Preco {

    //atributos
    @Column(length = 10, nullable = false)
    private BigDecimal valor;

    @Column(length = 10, nullable = false)
    private LocalDate dataAlteracao;

    @Column(length = 10, nullable = false)
    private LocalDate horaAlteracao;

    //construtor
    public Preco(BigDecimal valor, LocalDate dataAlteracao, LocalDate horaAlteracao) {
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
        this.valor = valor;
    }

    //getters
    public BigDecimal getValor() {
        return valor;
    }
    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }
    public LocalDate getHoraAlteracao() {
        return horaAlteracao;
    }

    //setters
    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    public void setHoraAlteracao(LocalDate horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
