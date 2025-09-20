package com.br.pdvpostodecombustivel.domain.repository;

import com.br.pdvpostodecombustivel.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcessoRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByNome(String nome);
    Optional<Pessoa> findByCpfCnpf(String cpfCnpj);

    boolean existsByCpfCnpj(String cpfCnpj);

    boolean existsByNome(String nome);
}