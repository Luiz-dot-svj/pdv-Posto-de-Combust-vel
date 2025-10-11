package com.br.pdvpostodecombustivel.domain.repository;

import com.br.pdvpostodecombustivel.domain.entity.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoRepository extends JpaRepository<Preco, Long> {
}
