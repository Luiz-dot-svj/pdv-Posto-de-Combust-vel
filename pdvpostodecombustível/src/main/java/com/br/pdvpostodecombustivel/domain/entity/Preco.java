
package com.br.pdvpostodecombustivel.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "preco")
public class Preco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataAlteracao;

    @Column(nullable = false)
    private LocalTime horaAlteracao;

    /** Construtor JPA */
    protected Preco() {}

    //construtor
    public Preco(BigDecimal valor, LocalDate dataAlteracao, LocalTime horaAlteracao) {
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
        this.valor = valor;
    }

    //getters
    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }
    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }
    public LocalTime getHoraAlteracao() {
        return horaAlteracao;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    public void setHoraAlteracao(LocalTime horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
