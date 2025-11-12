package com.br.pdvpostocombustivel.api.custo;

import com.br.pdvpostocombustivel.api.custo.dto.CustoRequest;
import com.br.pdvpostocombustivel.api.custo.dto.CustoResponse;
import com.br.pdvpostocombustivel.domain.entity.Custo;
import com.br.pdvpostocombustivel.domain.repository.CustoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class CustoService {

    private final CustoRepository repository;

    public CustoService(CustoRepository repository) {
        this.repository = repository;
    }

    public CustoResponse create(CustoRequest req) {
        validarUnicidadeDataProcessamento(req.dataProcessamento(), null);
        Custo novoCusto = toEntity(req);
        return toResponse(repository.save(novoCusto));
    }

    @Transactional(readOnly = true)
    public CustoResponse getById(Long id) {
        Custo c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));
        return toResponse(c);
    }

    @Transactional(readOnly = true)
    public CustoResponse getByDataProcessamento(LocalDate dataProcessamento) {
        Custo c = repository.findByDataProcessamento(dataProcessamento)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado para a data de processamento: " + dataProcessamento));
        return toResponse(c);
    }

    @Transactional(readOnly = true)
    public Page<CustoResponse> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public CustoResponse update(Long id, CustoRequest req) {
        Custo c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));

        if (req.dataProcessamento() != null && !req.dataProcessamento().equals(c.getDataProcessamento())) {
            validarUnicidadeDataProcessamento(req.dataProcessamento(), id);
        }

        c.setImposto(req.imposto());
        c.setCustoVariavel(req.custoVariavel());
        c.setCustoFixo(req.custoFixo());
        c.setMargemLucro(req.margemLucro());
        c.setDataProcessamento(req.dataProcessamento());
        c.setTipoCusto(req.tipoCusto());

        return toResponse(repository.save(c));
    }

    public CustoResponse patch(Long id, CustoRequest req) {
        Custo c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));

        if (req.imposto() != 0) c.setImposto(req.imposto());
        if (req.custoVariavel() != 0) c.setCustoVariavel(req.custoVariavel());
        if (req.custoFixo() != 0) c.setCustoFixo(req.custoFixo());
        if (req.margemLucro() != 0) c.setMargemLucro(req.margemLucro());
        if (req.dataProcessamento() != null) {
            if (!req.dataProcessamento().equals(c.getDataProcessamento())) {
                validarUnicidadeDataProcessamento(req.dataProcessamento(), id);
            }
            c.setDataProcessamento(req.dataProcessamento());
        }
        if (req.tipoCusto() != null) c.setTipoCusto(req.tipoCusto());

        return toResponse(repository.save(c));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Custo não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private void validarUnicidadeDataProcessamento(LocalDate dataProcessamento, Long idAtual) {
        repository.findByDataProcessamento(dataProcessamento).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("Já existe um custo cadastrado para a data: " + dataProcessamento);
            }
        });
    }

    private Custo toEntity(CustoRequest req) {
        return new Custo(
                req.imposto(),
                req.custoVariavel(),
                req.custoFixo(),
                req.margemLucro(),
                req.dataProcessamento(),
                req.tipoCusto()
        );
    }

    private CustoResponse toResponse(Custo c) {
        return new CustoResponse(
                c.getId(),
                c.getImposto(),
                c.getCustoVariavel(),
                c.getCustoFixo(),
                c.getMargemLucro(),
                c.getDataProcessamento(),
                c.getTipoCusto()
        );
    }
}
