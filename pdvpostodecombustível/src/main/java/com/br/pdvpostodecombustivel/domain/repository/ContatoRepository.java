package com.br.pdvpostodecombustivel.domain.repository;

import com.br.pdvpostodecombustivel.domain.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
