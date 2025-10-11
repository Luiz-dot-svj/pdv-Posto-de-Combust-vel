package com.br.pdvpostodecombustivel.api.produto;

import com.br.pdvpostodecombustivel.api.produto.dto.ProdutoRequest;
import com.br.pdvpostodecombustivel.api.produto.dto.ProdutoResponse;
import com.br.pdvpostodecombustivel.domain.entity.Produto;
import com.br.pdvpostodecombustivel.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponse create(ProdutoRequest req) {
        Produto novoProduto = toEntity(req);
        return toResponse(repository.save(novoProduto));
    }

    @Transactional(readOnly = true)
    public Page<ProdutoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public ProdutoResponse findById(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + id));
        return toResponse(produto);
    }

    public ProdutoResponse update(Long id, ProdutoRequest req) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + id));

        produto.setNome(req.nome());
        produto.setReferencia(req.referencia());
        produto.setFornecedor(req.fornecedor());
        produto.setMarca(req.marca());
        produto.setCategoria(req.categoria());

        return toResponse(repository.save(produto));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Produto toEntity(ProdutoRequest req) {
        return new Produto(
                req.nome(),
                req.referencia(),
                req.fornecedor(),
                req.marca(),
                req.categoria()
        );
    }

    private ProdutoResponse toResponse(Produto p) {
        return new ProdutoResponse(
                p.getId(),
                p.getNome(),
                p.getReferencia(),
                p.getFornecedor(),
                p.getMarca(),
                p.getCategoria()
        );
    }
}
