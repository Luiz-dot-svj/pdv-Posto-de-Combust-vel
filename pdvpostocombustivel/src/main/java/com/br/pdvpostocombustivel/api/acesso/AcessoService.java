package com.br.pdvpostocombustivel.api.acesso;


import com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoResponse;
import com.br.pdvpostocombustivel.domain.entity.Acesso;
import com.br.pdvpostocombustivel.domain.repository.AcessoRepository;
import com.br.pdvpostocombustivel.exception.AcessoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public AcessoResponse login(AcessoRequest req) {
        Acesso acesso = repository.findByUsuario(req.usuario())
                .orElseThrow(() -> new AcessoException("Usuário ou senha inválidos."));

        if (!acesso.getSenha().equals(req.senha())) {
            throw new AcessoException("Usuário ou senha inválidos.");
        }
        String generatedToken = "dummy-jwt-token-for-" + acesso.getUsuario();

        return new AcessoResponse(
                acesso.getId(),
                acesso.getUsuario(),
                acesso.getSenha(),
                acesso.getTipoAcesso(),
                generatedToken
        );
    }

    @Transactional(readOnly = true)
    public AcessoResponse getById(Long id) {
        Acesso p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public AcessoResponse getByUsuario(String usuario) {
        Acesso p = repository.findByUsuario(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. usuario=" + usuario));
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public Page<AcessoResponse> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public AcessoResponse update(Long id, AcessoRequest req) {
        Acesso p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));

        if (req.usuario() != null && !req.usuario().equals(p.getUsuario())) {
            validarUnicidadeUsuario(req.usuario(), id);
        }

        p.setSenha(req.senha());
        p.setUsuario(req.usuario());

        return toResponse(repository.save(p));
    }

    public AcessoResponse patch(Long id, AcessoRequest req) {
        Acesso p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));

        if (req.senha() != null)  p.setSenha(req.senha());
        if (req.usuario() != null) {
            if (!req.usuario().equals(p.getUsuario())) {
                validarUnicidadeUsuario(req.usuario(), id);
            }
            p.setUsuario(req.usuario());
        }

        return toResponse(repository.save(p));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Acesso não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private void validarUnicidadeUsuario(String usuario, Long idAtual) {
        repository.findByUsuario(usuario).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("Usuario já cadastrado: " + usuario);
            }
        });
    }

    private Acesso toEntity(AcessoRequest req) {
        return new Acesso(
                req.usuario(),
                req.senha(),
                req.tipoAcesso()
        );
    }

    private AcessoResponse toResponse(Acesso p) {
        return new AcessoResponse(
                p.getId(),
                p.getUsuario(),
                p.getSenha(),
                p.getTipoAcesso(),
                null
        );
    }
}
