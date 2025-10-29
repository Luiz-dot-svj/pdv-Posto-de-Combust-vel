
package com.br.pdvpostodecombustivel.api.pessoa;


import com.br.pdvpostodecombustivel.api.pessoa.dto.PessoaRequest;
import com.br.pdvpostodecombustivel.api.pessoa.dto.PessoaResponse;
import com.br.pdvpostodecombustivel.domain.entity.Pessoa;
import com.br.pdvpostodecombustivel.domain.repository.PessoaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public PessoaResponse create(PessoaRequest req) {
        Pessoa novaPessoa = toEntity(req);
        return toResponse(repository.save(novaPessoa));
    }

    @Transactional(readOnly = true)
    public PessoaResponse getById(Long id) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public Page<PessoaResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public PessoaResponse update(Long id, PessoaRequest req) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));

        if (req.cpfCnpj() != null && !req.cpfCnpj().equals(p.getCpfCnpj())) {
            validarUnicidadeCpfCnpj(req.cpfCnpj(), id);
        }

        p.setNomeCompleto(req.nomeCompleto());
        p.setCpfCnpj(req.cpfCnpj());
        p.setNumeroCtps(req.numeroCtps());
        p.setDataNascimento(req.dataNascimento());

        return toResponse(repository.save(p));
    }

    public PessoaResponse patch(Long id, PessoaRequest req) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));

        if (req.nomeCompleto() != null)  p.setNomeCompleto(req.nomeCompleto());
        if (req.cpfCnpj() != null) {
            if (!req.cpfCnpj().equals(p.getCpfCnpj())) {
                validarUnicidadeCpfCnpj(req.cpfCnpj(), id);
            }
            p.setCpfCnpj(req.cpfCnpj());
        }
        if (req.numeroCtps() != null)    p.setNumeroCtps(req.numeroCtps());
        if (req.dataNascimento() != null) p.setDataNascimento(req.dataNascimento());

        return toResponse(repository.save(p));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada. id=" + id);
        }
        repository.deleteById(id);
    }

    private void validarUnicidadeCpfCnpj(String cpfCnpj, Long idAtual) {
        repository.findByCpfCnpj(cpfCnpj).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("CPF/CNPJ já cadastrado: " + cpfCnpj);
            }
        });
    }

    private Pessoa toEntity(PessoaRequest req) {
        return new Pessoa(
                req.nomeCompleto(),
                req.cpfCnpj(),
                req.numeroCtps(),
                req.dataNascimento(),
                req.tipoPessoa()
        );
    }

    private PessoaResponse toResponse(Pessoa p) {
        return new PessoaResponse(
                p.getId(),
                p.getNomeCompleto(),
                p.getCpfCnpj(),
                p.getNumeroCtps(),
                p.getDataNascimento()
        );
    }
}
