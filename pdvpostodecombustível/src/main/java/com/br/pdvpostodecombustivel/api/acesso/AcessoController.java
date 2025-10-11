package com.br.pdvpostodecombustivel.api.acesso;

import com.br.pdvpostodecombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostodecombustivel.api.acesso.dto.AcessoResponse;
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
@RequestMapping("/api/acessos")
@Tag(name = "Acessos", description = "Operações relacionadas aos dados de acesso")
public class AcessoController {

    private final AcessoService service;

    public AcessoController(AcessoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um novo acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acesso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<AcessoResponse> create(@Valid @RequestBody AcessoRequest request) {
        AcessoResponse response = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os acessos de forma paginada")
    public ResponseEntity<Page<AcessoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um acesso pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso encontrado"),
            @ApiResponse(responseCode = "404", description = "Acesso não encontrado")
    })
    public ResponseEntity<AcessoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um acesso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acesso não encontrado")
    })
    public ResponseEntity<AcessoResponse> update(@PathVariable Long id, @Valid @RequestBody AcessoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um acesso pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Acesso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acesso não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
