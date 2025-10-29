package com.br.pdvpostodecombustivel.api.contato;

import com.br.pdvpostodecombustivel.api.contato.dto.ContatoRequest;
import com.br.pdvpostodecombustivel.api.contato.dto.ContatoResponse;
import com.br.pdvpostodecombustivel.domain.entity.Contato;
import com.br.pdvpostodecombustivel.domain.repository.ContatoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContatoService {

    private final ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public ContatoResponse create(ContatoRequest req) {
        Contato novoContato = toEntity(req);
        return toResponse(repository.save(novoContato));
    }

    @Transactional(readOnly = true)
    public Page<ContatoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public ContatoResponse findById(Long id) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));
        return toResponse(contato);
    }

    public ContatoResponse update(Long id, ContatoRequest req) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));

        contato.setTelefone(req.telefone());
        contato.setEmail(req.email());
        contato.setEndereco(req.endereco());

        return toResponse(repository.save(contato));
    }

    public ContatoResponse patch(Long id, ContatoRequest req) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));

        if (req.telefone() != null) contato.setTelefone(req.telefone());
        if (req.email() != null) contato.setEmail(req.email());
        if (req.endereco() != null) contato.setEndereco(req.endereco());

        return toResponse(repository.save(contato));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Contato não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Contato toEntity(ContatoRequest req) {
        return new Contato(
                req.telefone(),
                req.email(),
                req.endereco()
        );
    }

    private ContatoResponse toResponse(Contato c) {
        return new ContatoResponse(
                c.getId(),
                c.getTelefone(),
                c.getEmail(),
                c.getEndereco()
        );
    }
}
