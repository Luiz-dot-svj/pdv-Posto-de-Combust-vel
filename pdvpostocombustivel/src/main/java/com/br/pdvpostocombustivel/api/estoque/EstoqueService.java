package com.br.pdvpostocombustivel.api.estoque;

import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import com.br.pdvpostocombustivel.enums.TipoEstoque;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class EstoqueService {

    private final EstoqueRepository repository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public EstoqueResponse create(EstoqueRequest req) {
        validarUnicidadeLoteFabricacao(req.loteFabricacao(), null);
        Estoque novoEstoque = toEntity(req);
        return toResponse(repository.save(novoEstoque));
    }

    @Transactional(readOnly = true)
    public EstoqueResponse getById(Long id) {
        Estoque e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));
        return toResponse(e);
    }

    @Transactional(readOnly = true)
    public EstoqueResponse getByLoteFabricacao(String loteFabricacao) {
        Estoque e = repository.findByLoteFabricacao(loteFabricacao)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. loteFabricacao=" + loteFabricacao));
        return toResponse(e);
    }

    @Transactional(readOnly = true)
    public Page<EstoqueResponse> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public EstoqueResponse update(Long id, EstoqueRequest req) {
        Estoque e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));

        if (req.loteFabricacao() != null && !req.loteFabricacao().equals(e.getLoteFabricacao())) {
            validarUnicidadeLoteFabricacao(req.loteFabricacao(), id);
        }

        e.setQuantidade(req.quantidade());
        e.setLocalTanque(req.localTanque());
        e.setLocalEndereco(req.localEndereco());
        e.setLoteFabricacao(req.loteFabricacao());
        e.setDataValidade(req.dataValidade());
        e.setTipoEstoque(req.tipoEstoque());
        if (req.produtoId() != null) {
            Produto produto = produtoRepository.findById(req.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + req.produtoId()));
            e.setProduto(produto);
        }

        return toResponse(repository.save(e));
    }

    public EstoqueResponse patch(Long id, EstoqueRequest req) {
        Estoque e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));

        if (req.quantidade() != null) e.setQuantidade(req.quantidade());
        if (req.localTanque() != null) e.setLocalTanque(req.localTanque());
        if (req.localEndereco() != null) e.setLocalEndereco(req.localEndereco());
        if (req.loteFabricacao() != null) {
            if (!req.loteFabricacao().equals(e.getLoteFabricacao())) {
                validarUnicidadeLoteFabricacao(req.loteFabricacao(), id);
            }
            e.setLoteFabricacao(req.loteFabricacao());
        }
        if (req.dataValidade() != null) e.setDataValidade(req.dataValidade());
        if (req.tipoEstoque() != null) e.setTipoEstoque(req.tipoEstoque());
        if (req.produtoId() != null) {
            Produto produto = produtoRepository.findById(req.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + req.produtoId()));
            e.setProduto(produto);
        }

        return toResponse(repository.save(e));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Estoque não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private void validarUnicidadeLoteFabricacao(String loteFabricacao, Long idAtual) {
        repository.findByLoteFabricacao(loteFabricacao).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("Lote de Fabricação já cadastrado: " + loteFabricacao);
            }
        });
    }

    private Estoque toEntity(EstoqueRequest req) {
        Produto produto = produtoRepository.findById(req.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. id=" + req.produtoId()));

        return new Estoque(
                req.quantidade(),
                req.localTanque(),
                req.localEndereco(),
                req.loteFabricacao(),
                req.dataValidade(),
                req.tipoEstoque(),
                produto
        );
    }

    private EstoqueResponse toResponse(Estoque e) {
        return new EstoqueResponse(
                e.getId(),
                e.getQuantidade(),
                e.getLocalTanque(),
                e.getLocalEndereco(),
                e.getLoteFabricacao(),
                e.getDataValidade(),
                e.getTipoEstoque(),
                e.getProduto().getId()
        );
    }
}
