package com.aluguelcarro.aluguel.dominio.builder;

import com.aluguelcarro.aluguel.dominio.Cliente;
import com.github.javafaker.Faker;

import java.lang.reflect.Field;

public class ClienteBuilder {

    private String nomeCompleto;
    private String email;
    private String cpf;
    private Long id;
    private Faker faker;

    public ClienteBuilder() {
        faker = new Faker();
        nomeCompleto = faker.witcher().character();
        email = "email@email.com";
        cpf = "18065252087";
    }

    public ClienteBuilder comId(long id) {
        this.id = id;
        return this;
    }

    public Cliente criar() {
        return new Cliente(nomeCompleto, email, cpf);
    }

    public Cliente criarComId() {
        Cliente cliente = new Cliente(nomeCompleto, email, cpf);

        try {
            Field idField = cliente.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(cliente, id);
            idField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return cliente;
    }
}
