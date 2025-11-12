
package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.BombaStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "bomba")
public class Bomba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BombaStatus status;

    public Bomba() {
    }

    public Bomba(String nome, BombaStatus status) {
        this.nome = nome;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BombaStatus getStatus() {
        return status;
    }

    public void setStatus(BombaStatus status) {
        this.status = status;
    }
}
