package com.br.pdvpostocombustivel.domain.repository;


import com.br.pdvpostocombustivel.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);

    Optional<Produto> findByReferencia(String referencia);

    Optional<Produto> findByFornecedor(String fornecedor);

    Optional<Produto> findByMarca(String marca);

    Optional<Produto> findByCategoria(String categoria);

    boolean existsByNome(String nome);

    boolean existsByFornecedor(String fornecedor);

    boolean existsByMarca(String marca);

    boolean existsByCategoria(String categoria);


}
