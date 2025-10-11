package com.br.pdvpostodecombustivel.api.preco;

import com.br.pdvpostodecombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostodecombustivel.api.preco.dto.PrecoResponse;
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
@RequestMapping("/api/precos")
@Tag(name = "Preços", description = "Operações relacionadas a preços")
public class PrecoController {

    private final PrecoService service;

    public PrecoController(PrecoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um novo preço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Preço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<PrecoResponse> create(@Valid @RequestBody PrecoRequest request) {
        PrecoResponse response = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os preços de forma paginada")
    public ResponseEntity<Page<PrecoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um preço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço encontrado"),
            @ApiResponse(responseCode = "404", description = "Preço não encontrado")
    })
    public ResponseEntity<PrecoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um preço existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Preço não encontrado")
    })
    public ResponseEntity<PrecoResponse> update(@PathVariable Long id, @Valid @RequestBody PrecoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um preço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Preço não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
