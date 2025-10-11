package com.br.pdvpostodecombustivel.api.custo;

import com.br.pdvpostodecombustivel.api.custo.dto.CustoRequest;
import com.br.pdvpostodecombustivel.api.custo.dto.CustoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/custos")
@Tag(name = "Custos", description = "Operações relacionadas a custos")
public class CustoController {

    private final CustoService service;

    public CustoController(CustoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um novo custo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Custo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<CustoResponse> create(@Valid @RequestBody CustoRequest request) {
        CustoResponse response = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os custos de forma paginada")
    public ResponseEntity<Page<CustoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um custo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custo encontrado"),
            @ApiResponse(responseCode = "404", description = "Custo não encontrado")
    })
    public ResponseEntity<CustoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um custo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Custo não encontrado")
    })
    public ResponseEntity<CustoResponse> update(@PathVariable Long id, @Valid @RequestBody CustoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um custo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Custo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Custo não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
