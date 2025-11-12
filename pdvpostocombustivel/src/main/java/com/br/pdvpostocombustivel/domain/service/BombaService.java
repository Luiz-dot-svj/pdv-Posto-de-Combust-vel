package com.br.pdvpostocombustivel.domain.service;

import com.br.pdvpostocombustivel.domain.entity.Bomba;
import com.br.pdvpostocombustivel.domain.repository.BombaRepository;
import com.br.pdvpostocombustivel.enums.BombaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BombaService {

    @Autowired
    private BombaRepository bombaRepository;

    public List<Bomba> buscarTodas() {
        return bombaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Bomba atualizarStatus(Long id, BombaStatus status) {
        Bomba bomba = bombaRepository.findById(id).orElseThrow(() -> new RuntimeException("Bomba não encontrada"));
        bomba.setStatus(status);
        return bombaRepository.save(bomba);
    }
}
