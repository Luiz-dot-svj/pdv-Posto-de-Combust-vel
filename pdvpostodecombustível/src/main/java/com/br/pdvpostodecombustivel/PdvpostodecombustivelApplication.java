package com.br.pdvpostodecombustivel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.br.pdvpostodecombustivel.domain.entity.Pessoa;

@SpringBootApplication
public class PdvpostodecombustivelApplication {

	public static void main(String[] args) {

        //SpringApplication.run(PdvpostodecombustivelApplication.class, args);

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNomeCompleto("Rudson Pereira");
        pessoa1.setCpfCnpj("12345674910");
        pessoa1.setNumeroCtps(1223L);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNomeCompleto("Fulano");
        pessoa2.setCpfCnpj("12345674910");
        pessoa2.setNumeroCtps(1223L);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNomeCompleto("Jose Pereira");
        pessoa3.setCpfCnpj("12345674910");
        pessoa3.setNumeroCtps(1223L);

        System.out.println("Nome Completo: " + pessoa1.getNomeCompleto());
        System.out.println("CPF/CNPJ: " + pessoa1.getCpfCnpj());
        System.out.println("Numero CTPs: " + pessoa1.getNumeroCtps());
        System.out.println("------------------------------------");

        System.out.println("Nome Completo: " + pessoa2.getNomeCompleto());
        System.out.println("CPF/CNPJ: " + pessoa2.getCpfCnpj());
        System.out.println("Numero CTPs: " + pessoa2.getNumeroCtps());
        System.out.println("------------------------------------");

        System.out.println("Nome Completo: " + pessoa3.getNomeCompleto());
        System.out.println("CPF/CNPJ: " + pessoa3.getCpfCnpj());
        System.out.println("Numero CTPs: " + pessoa3.getNumeroCtps());
    }
}




