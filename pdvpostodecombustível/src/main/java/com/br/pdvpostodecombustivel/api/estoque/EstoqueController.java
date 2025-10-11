package com.br.pdvpostodecombustivel.api.estoque;

import com.br.pdvpostodecombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostodecombustivel.api.estoque.dto.EstoqueResponse;
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
@RequestMapping("/api/estoques")
@Tag(name = "Estoques", description = "Operações relacionadas a estoques")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um novo estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estoque criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EstoqueResponse> create(@Valid @RequestBody EstoqueRequest request) {
        EstoqueResponse response = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os estoques de forma paginada")
    public ResponseEntity<Page<EstoqueResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um estoque pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    public ResponseEntity<EstoqueResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um estoque existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    public ResponseEntity<EstoqueResponse> update(@PathVariable Long id, @Valid @RequestBody EstoqueRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um estoque pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estoque deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
