package com.br.pdvpostodecombustivel.api.acesso;

import com.br.pdvpostodecombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostodecombustivel.api.acesso.dto.AcessoResponse;
import com.br.pdvpostodecombustivel.domain.entity.Acesso;
import com.br.pdvpostodecombustivel.domain.repository.AcessoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AcessoService {

    private final AcessoRepository repository;

    public AcessoService(AcessoRepository repository) {
        this.repository = repository;
    }

    public AcessoResponse create(AcessoRequest req) {
        Acesso novoAcesso = toEntity(req);
        return toResponse(repository.save(novoAcesso));
    }

    @Transactional(readOnly = true)
    public Page<AcessoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public AcessoResponse findById(Long id) {
        Acesso acesso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));
        return toResponse(acesso);
    }

    public AcessoResponse update(Long id, AcessoRequest req) {
        Acesso acesso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));

        acesso.setUsuario(req.usuario());
        acesso.setSenha(req.senha());

        return toResponse(repository.save(acesso));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Acesso não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Acesso toEntity(AcessoRequest req) {
        return new Acesso(
                req.usuario(),
                req.senha()
        );
    }

    private AcessoResponse toResponse(Acesso a) {
        return new AcessoResponse(
                a.getId(),
                a.getUsuario()
        );
    }
}
