package com.br.pdvpostocombustivel.domain.entity;
import com.br.pdvpostocombustivel.enums.TipoEstoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private BigDecimal quantidade;

    @Column(length = 50, nullable = false)
    private String localTanque;

    @Column(length = 100, nullable = false)
    private String localEndereco;

    @Column(length = 10, nullable = false)
    private String loteFabricacao;

    @Column(length = 10, nullable = false)
    private LocalDate dataValidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_estoque", nullable = false, length = 15)
    private TipoEstoque tipoEstoque;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public Estoque() {
    }
    
    public  Estoque(BigDecimal quantidade, String localTanque, String localEndereco, String loteFabricacao,LocalDate dataValidade, TipoEstoque tipoEstoque, Produto produto) {
        this.quantidade = quantidade;
        this.localTanque = localTanque;
        this.localEndereco = localEndereco;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
        this.tipoEstoque = tipoEstoque;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
    public String getLocalTanque() {
        return localTanque;
    }
    public String getLocalEndereco() {
        return localEndereco;
    }
    public String getLoteFabricacao() {
        return loteFabricacao;
    }
    public LocalDate getDataValidade(){
        return dataValidade;
    }
    public TipoEstoque getTipoEstoque() {
        return tipoEstoque;
    }
    public Produto getProduto() {
        return produto;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    public void setLocalEndereco(String localEndereco) {
        this.localEndereco = localEndereco;
    }
    public void setLocalTanque(String localTanque) {
        this.localTanque = localTanque;
    }
    public void setLoteFabricacao(String loteFabricacao) {
        this.loteFabricacao = loteFabricacao;
    }
    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
    public void setTipoEstoque(TipoEstoque tipoEstoque) {
        this.tipoEstoque = tipoEstoque;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
