package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoAcesso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String usuario;

    @Column(length = 15, nullable = false)
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acesso", nullable = false, length = 30)
    private TipoAcesso tipoAcesso;

    public Acesso() {
    }
    
    public Acesso(String usuario, String senha, TipoAcesso tipoAcesso) {
        this.usuario = usuario;
        this.senha =senha;
        this.tipoAcesso = tipoAcesso;
    }

    public Long getId() {
        return id;
    }
    
    public String getSenha() {
        return senha;
    }
    public String getUsuario() {
        return usuario;
    }
    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}
