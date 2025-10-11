package com.br.pdvpostodecombustivel.domain.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "custos")
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double imposto;
    private Double custoVariavel;
    private Double custoFixo;
    private Double margemLucro;
    private Date dataProcessamento;

    /** Construtor JPA */
    protected Custo() {}

    public Custo(Double imposto, Double custoVariavel, Double custoFixo, Double margemLucro, Date dataProcessamento) {
        this.imposto = imposto;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }

    // Getters
    public Long getId() { return id; }
    public Double getImposto() { return imposto; }
    public Double getCustoVariavel() { return custoVariavel; }
    public Double getCustoFixo() { return custoFixo; }
    public Double getMargemLucro() { return margemLucro; }
    public Date getDataProcessamento() { return dataProcessamento; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setImposto(Double imposto) { this.imposto = imposto; }
    public void setCustoVariavel(Double custoVariavel) { this.custoVariavel = custoVariavel; }
    public void setCustoFixo(Double custoFixo) { this.custoFixo = custoFixo; }
    public void setMargemLucro(Double margemLucro) { this.margemLucro = margemLucro; }
    public void setDataProcessamento(Date dataProcessamento) { this.dataProcessamento = dataProcessamento; }
}
