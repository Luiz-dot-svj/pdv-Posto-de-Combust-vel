package com.br.pdvpostocombustivel.api.bomba;

import com.br.pdvpostocombustivel.domain.entity.Bomba;
import com.br.pdvpostocombustivel.domain.service.BombaService;
import com.br.pdvpostocombustivel.enums.BombaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bombas")
public class BombaController {

    @Autowired
    private BombaService bombaService;

    @GetMapping
    public List<Bomba> buscarTodas() {
        List<Bomba> bombas = bombaService.buscarTodas();
        return bombas;
    }

    @PutMapping("/{id}/status")
    public Bomba atualizarStatus(@PathVariable Long id, @RequestBody BombaStatus status) {
        return bombaService.atualizarStatus(id, status);
    }
}
