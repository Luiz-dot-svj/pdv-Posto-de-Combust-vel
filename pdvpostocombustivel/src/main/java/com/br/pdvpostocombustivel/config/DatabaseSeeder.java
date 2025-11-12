package com.br.pdvpostocombustivel.config;

import com.br.pdvpostocombustivel.domain.entity.Bomba;
import com.br.pdvpostocombustivel.domain.repository.BombaRepository;
import com.br.pdvpostocombustivel.enums.BombaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private BombaRepository bombaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (bombaRepository.count() == 0) {
            bombaRepository.save(new Bomba("B1", BombaStatus.INATIVA));
            bombaRepository.save(new Bomba("B2", BombaStatus.INATIVA));
            bombaRepository.save(new Bomba("B3", BombaStatus.INATIVA));
        }
    }
}
