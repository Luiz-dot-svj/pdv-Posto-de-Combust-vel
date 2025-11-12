package com.br.pdvpostocombustivel.api.contato;

import com.br.pdvpostocombustivel.api.contato.dto.ContatoRequest;
import com.br.pdvpostocombustivel.api.contato.dto.ContatoResponse;
import com.br.pdvpostocombustivel.domain.entity.Contato;
import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import com.br.pdvpostocombustivel.domain.repository.ContatoRepository;
import com.br.pdvpostocombustivel.domain.repository.PessoaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContatoService {
    private final ContatoRepository repository;
    private final PessoaRepository pessoaRepository;

    public ContatoService(ContatoRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public ContatoResponse create(ContatoRequest req) {
        Contato novoContato = toEntity(req);
        return toResponse(repository.save(novoContato));
    }

    @Transactional(readOnly = true)
    public ContatoResponse getById(Long id) {
        Contato p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public ContatoResponse getByEmail(String email) {
        Contato p = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. email=" + email));
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public Page<ContatoResponse> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public ContatoResponse update(Long id, ContatoRequest req) {
        Contato p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));

        if (req.email() != null && !req.email().equals(p.getEmail())) {
            validarUnicidadeEmail(req.email(), id);
        }

        updateEntityFromRequest(p, req);

        return toResponse(repository.save(p));
    }

    public ContatoResponse patch(Long id, ContatoRequest req) {
        Contato p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));

        if (req.endereco() != null)  p.setEndereco(req.endereco());
        if (req.email() != null) {
            if (!req.email().equals(p.getEmail())) {
                validarUnicidadeEmail(req.email(), id);
            }
            p.setEmail(req.email());
        }
        if (req.pessoaId() != null) {
            Pessoa pessoa = pessoaRepository.findById(req.pessoaId())
                    .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrado. id=" + req.pessoaId()));
            p.setPessoa(pessoa);
        }
        if (req.telefone() != null) p.setTelefone(req.telefone());
        if (req.tipoContato() != null) p.setTipoContato(req.tipoContato());


        return toResponse(repository.save(p));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Contato não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private void validarUnicidadeEmail(String email, Long idAtual) {
        repository.findByEmail(email).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("Email já cadastrado: " + email);
            }
        });
    }

    private Contato toEntity(ContatoRequest req) {
        Pessoa pessoa = pessoaRepository.findById(req.pessoaId())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + req.pessoaId()));
        return new Contato(
                pessoa,
                req.telefone(),
                req.email(),
                req.endereco(),
                req.tipoContato()
        );
    }

    private void updateEntityFromRequest(Contato contato, ContatoRequest request) {
        Pessoa pessoa = pessoaRepository.findById(request.pessoaId())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + request.pessoaId()));
        contato.setPessoa(pessoa);
        contato.setTelefone(request.telefone());
        contato.setEmail(request.email());
        contato.setEndereco(request.endereco());
        contato.setTipoContato(request.tipoContato());
    }

    private ContatoResponse toResponse(Contato p) {
        return new ContatoResponse(
                p.getId(),
                p.getTelefone(),
                p.getEmail(),
                p.getEndereco(),
                p.getTipoContato(),
                p.getPessoa() != null ? p.getPessoa().getId() : null
        );
    }
}
