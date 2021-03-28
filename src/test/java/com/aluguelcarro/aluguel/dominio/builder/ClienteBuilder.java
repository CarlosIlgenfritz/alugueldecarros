package com.aluguelcarro.aluguel.dominio.builder;

import com.aluguelcarro.aluguel.dominio.Cliente;
import com.github.javafaker.Faker;

public class ClienteBuilder {

    private String nomeCompleto;
    private String email;
    private String cpf;
    private Faker faker;

    public ClienteBuilder() {
        faker = new Faker();
        nomeCompleto = faker.witcher().character();
        email = "email@email.com";
        cpf = "18065252087";
    }

    public Cliente criar() {
        return new Cliente(nomeCompleto, email, cpf);
    }
}
