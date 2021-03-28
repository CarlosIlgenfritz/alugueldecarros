package com.aluguelcarro.aluguel.dominio;

import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Faker faker;
    private String nomeCompleto;
    private String email;
    private String cpf;

    @BeforeEach
    void init() {
        faker = new Faker();
        nomeCompleto = faker.witcher().character();
        email = "email@email.com";
        cpf = faker.number().digits(11);
    }

    @Test
    void deve_instanciar_cliente() {

        Cliente cliente = new Cliente(nomeCompleto, email, cpf);

        assertEquals(nomeCompleto, cliente.getNomeCompleto());
        assertEquals(email, cliente.getEmail());
        assertEquals(cpf, cliente.getCpf());

    }

    @Test
    void nao_deve_instanciar_cliente_sem_nome_completo() {
        String nomeCompleto = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Cliente(nomeCompleto, email, cpf);
        });

        assertEquals("Não é possível criar um cliente sem modelo.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_cliente_sem_email() {
        String email = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Cliente(nomeCompleto, email, cpf);
        });

        assertEquals("Não é possível criar um cliente sem email.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_cliente_sem_cpf() {
        String cpf = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Cliente(nomeCompleto, email, cpf);
        });

        assertEquals("Não é possível criar um cliente sem cpf.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_cliente_sem_cpf_valido() {
        String cpf = "11111111111";

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Cliente(nomeCompleto, email, cpf);
        });

        assertEquals("Não é possível criar um cliente com cpf inválido.", excecaoDeDominio.getMessage());
    }

}