package com.br.pdvpostodecombustivel.api.preco;

import com.br.pdvpostodecombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostodecombustivel.api.preco.dto.PrecoResponse;
import com.br.pdvpostodecombustivel.domain.entity.Preco;
import com.br.pdvpostodecombustivel.domain.repository.PrecoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrecoService {

    private final PrecoRepository repository;

    public PrecoService(PrecoRepository repository) {
        this.repository = repository;
    }

    public PrecoResponse create(PrecoRequest req) {
        Preco novoPreco = toEntity(req);
        return toResponse(repository.save(novoPreco));
    }

    @Transactional(readOnly = true)
    public Page<PrecoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public PrecoResponse findById(Long id) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));
        return toResponse(preco);
    }

    public PrecoResponse update(Long id, PrecoRequest req) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));

        preco.setValor(req.valor());
        preco.setDataAlteracao(req.dataAlteracao());
        preco.setHoraAlteracao(req.horaAlteracao());

        return toResponse(repository.save(preco));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Preço não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Preco toEntity(PrecoRequest req) {
        return new Preco(
                req.valor(),
                req.dataAlteracao(),
                req.horaAlteracao()
        );
    }

    private PrecoResponse toResponse(Preco p) {
        return new PrecoResponse(
                p.getId(),
                p.getValor(),
                p.getDataAlteracao(),
                p.getHoraAlteracao()
        );
    }
}
