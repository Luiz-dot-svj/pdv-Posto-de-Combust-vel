package com.br.pdvpostodecombustivel.api.custo;

import com.br.pdvpostodecombustivel.api.custo.dto.CustoRequest;
import com.br.pdvpostodecombustivel.api.custo.dto.CustoResponse;
import com.br.pdvpostodecombustivel.domain.entity.Custo;
import com.br.pdvpostodecombustivel.domain.repository.CustoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustoService {

    private final CustoRepository repository;

    public CustoService(CustoRepository repository) {
        this.repository = repository;
    }

    public CustoResponse create(CustoRequest req) {
        Custo novoCusto = toEntity(req);
        return toResponse(repository.save(novoCusto));
    }

    @Transactional(readOnly = true)
    public Page<CustoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public CustoResponse findById(Long id) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));
        return toResponse(custo);
    }

    public CustoResponse update(Long id, CustoRequest req) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));

        custo.setImposto(req.imposto());
        custo.setCustoVariavel(req.custoVariavel());
        custo.setCustoFixo(req.custoFixo());
        custo.setMargemLucro(req.margemLucro());
        custo.setDataProcessamento(req.dataProcessamento());

        return toResponse(repository.save(custo));
    }

    public CustoResponse patch(Long id, CustoRequest req) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));

        if (req.imposto() != null) custo.setImposto(req.imposto());
        if (req.custoVariavel() != null) custo.setCustoVariavel(req.custoVariavel());
        if (req.custoFixo() != null) custo.setCustoFixo(req.custoFixo());
        if (req.margemLucro() != null) custo.setMargemLucro(req.margemLucro());
        if (req.dataProcessamento() != null) custo.setDataProcessamento(req.dataProcessamento());

        return toResponse(repository.save(custo));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Custo não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Custo toEntity(CustoRequest req) {
        return new Custo(
                req.imposto(),
                req.custoVariavel(),
                req.custoFixo(),
                req.margemLucro(),
                req.dataProcessamento()
        );
    }

    private CustoResponse toResponse(Custo c) {
        return new CustoResponse(
                c.getId(),
                c.getImposto(),
                c.getCustoVariavel(),
                c.getCustoFixo(),
                c.getMargemLucro(),
                c.getDataProcessamento()
        );
    }
}
