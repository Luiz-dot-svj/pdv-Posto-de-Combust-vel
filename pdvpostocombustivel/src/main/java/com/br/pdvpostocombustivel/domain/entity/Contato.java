package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoContato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(length = 14, nullable = false)
    private String telefone;

    @Column (length = 50, nullable = false)
    private String email;

    @Column (length = 100, nullable = false)
    private String endereco;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato", nullable = false, length = 30)
    private TipoContato tipoContato;

    public Contato() {
    }

    public Contato(Pessoa pessoa, String telefone, String email, String endereco, TipoContato tipoContato) {
        this.pessoa = pessoa;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.tipoContato = tipoContato;
    }

    public Long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }
}
