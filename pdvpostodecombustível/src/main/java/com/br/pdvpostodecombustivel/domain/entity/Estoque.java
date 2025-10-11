package com.br.pdvpostodecombustivel.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal quantidade;
    private String localTanque;
    private String localEndereco;
    private String loteFabricacao;
    private LocalDate dataValidade;

    /** Construtor JPA */
    protected Estoque() {}

    public Estoque(BigDecimal quantidade, String localTanque, String localEndereco, String loteFabricacao, LocalDate dataValidade){
        this.quantidade = quantidade;
        this.localTanque = localTanque;
        this.localEndereco = localEndereco;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public String getLocalTanque(){
        return localTanque;
    }

    public String getLocalEndereco() {
        return localEndereco;
    }

    public String getLoteFabricacao() {
        return loteFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setLocalTanque(String localTanque) {
        this.localTanque = localTanque;
    }

    public void setLocalEndereco(String localEndereco) {
        this.localEndereco = localEndereco;
    }

    public void setLoteFabricacao(String loteFabricacao) {
        this.loteFabricacao = loteFabricacao;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
