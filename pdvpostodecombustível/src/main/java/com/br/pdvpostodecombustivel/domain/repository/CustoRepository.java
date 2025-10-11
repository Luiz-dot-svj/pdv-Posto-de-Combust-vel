package com.br.pdvpostodecombustivel.domain.repository;

import com.br.pdvpostodecombustivel.domain.entity.Custo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustoRepository extends JpaRepository<Custo, Long> {
}
