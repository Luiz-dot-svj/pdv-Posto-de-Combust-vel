package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByLoteFabricacao(String loteFabricacao);

    boolean existsByLoteFabricacao(String loteFabricacao);

    Optional<Estoque> findByLocalTanque(String localTanque);

    boolean existsByLocalTanque(String localTanque);

    Optional<Estoque> findByLocalEndereco(String localEndereco);

    boolean existsByLocalEndereco(String localEndereco);

    Optional<Estoque> findByDataValidade(LocalDate dataValidade);

}
