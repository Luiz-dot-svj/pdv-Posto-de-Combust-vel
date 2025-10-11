package com.br.pdvpostodecombustivel.api.estoque;

import com.br.pdvpostodecombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostodecombustivel.api.estoque.dto.EstoqueResponse;
import com.br.pdvpostodecombustivel.domain.entity.Estoque;
import com.br.pdvpostodecombustivel.domain.repository.EstoqueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public EstoqueResponse create(EstoqueRequest req) {
        Estoque novoEstoque = toEntity(req);
        return toResponse(repository.save(novoEstoque));
    }

    @Transactional(readOnly = true)
    public Page<EstoqueResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public EstoqueResponse findById(Long id) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));
        return toResponse(estoque);
    }

    public EstoqueResponse update(Long id, EstoqueRequest req) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));

        estoque.setQuantidade(req.quantidade());
        estoque.setLocalTanque(req.localTanque());
        estoque.setLocalEndereco(req.localEndereco());
        estoque.setLoteFabricacao(req.loteFabricacao());
        estoque.setDataValidade(req.dataValidade());

        return toResponse(repository.save(estoque));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Estoque não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Estoque toEntity(EstoqueRequest req) {
        return new Estoque(
                req.quantidade(),
                req.localTanque(),
                req.localEndereco(),
                req.loteFabricacao(),
                req.dataValidade()
        );
    }

    private EstoqueResponse toResponse(Estoque e) {
        return new EstoqueResponse(
                e.getId(),
                e.getQuantidade(),
                e.getLocalTanque(),
                e.getLocalEndereco(),
                e.getLoteFabricacao(),
                e.getDataValidade()
        );
    }
}
