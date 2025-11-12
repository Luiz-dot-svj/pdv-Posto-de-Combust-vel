package com.br.pdvpostocombustivel.api.preco;

import com.br.pdvpostocombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostocombustivel.api.preco.dto.PrecoResponse;
import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrecoService {

    private final PrecoRepository repository;
    private final ProdutoRepository produtoRepository;

    public PrecoService(PrecoRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public PrecoResponse create(PrecoRequest req) {
        Preco novoPreco = toEntity(req);
        return toResponse(repository.save(novoPreco));
    }

    @Transactional(readOnly = true)
    public PrecoResponse getById(Long id) {
        Preco p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public Page<PrecoResponse> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public PrecoResponse update(Long id, PrecoRequest req) {
        Preco p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));

        p.setValor(req.valor());
        p.setDataAlteracao(req.dataAlteracao());
        p.setHoraAlteracao(req.horaAlteracao());
        if (req.produtoId() != null) {
            Produto produto = produtoRepository.findById(req.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + req.produtoId()));
            p.setProduto(produto);
        }

        return toResponse(repository.save(p));
    }

    public PrecoResponse patch(Long id, PrecoRequest req) {
        Preco p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));

        if (req.valor() != null) p.setValor(req.valor());
        if (req.dataAlteracao() != null) p.setDataAlteracao(req.dataAlteracao());
        if (req.horaAlteracao() != null) p.setHoraAlteracao(req.horaAlteracao());
        if (req.produtoId() != null) {
            Produto produto = produtoRepository.findById(req.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + req.produtoId()));
            p.setProduto(produto);
        }

        return toResponse(repository.save(p));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Preço não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Preco toEntity(PrecoRequest req) {
        Produto produto = produtoRepository.findById(req.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + req.produtoId()));

        return new Preco(
                req.valor(),
                req.dataAlteracao(),
                req.horaAlteracao(),
                produto
        );
    }

    private PrecoResponse toResponse(Preco p) {
        return new PrecoResponse(
                p.getId(),
                p.getDataAlteracao(),
                p.getHoraAlteracao(),
                p.getValor(),
                p.getProduto().getId()
        );
    }
}
