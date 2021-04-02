package com.aluguelcarro.aluguel.dominio;

import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AluguelTest {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long cliente;
    private Long carro;
    private Faker faker;
    private Double valorDoAluguel;

    @BeforeEach
    void init() {
        faker = new Faker();
        dataInicio = LocalDate.now();
        dataFim = LocalDate.now().plusDays(2);
        cliente = faker.random().nextLong();
        carro = faker.random().nextLong();
        valorDoAluguel = faker.number().randomDouble(1,2,10);
    }

    @Test
    void deve_instanciar_aluguel() {

        Aluguel aluguel = new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);

        assertEquals(dataInicio, aluguel.getDataInicio());
        assertEquals(dataFim, aluguel.getDataFim());
        assertEquals(cliente, aluguel.getClienteId());
        assertEquals(carro, aluguel.getCarroId());
        assertEquals(valorDoAluguel, aluguel.getValorDoAluguel());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_data_inicio() {
        LocalDate dataInicio = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel sem data de inicio.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_data_final() {
        LocalDate dataFim = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel sem data final.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_um_cliente() {
        Long cliente = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel sem um cliente.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_um_carro() {
        Long carro = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel sem um carro.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_com_data_inicio_menor_que_a_data_atual() {
        LocalDate dataInicio = LocalDate.now().minusDays(2);

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel com a data inicial menor que a data atual.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_com_data_inicio_menor_que_data_final() {
        LocalDate dataFim = LocalDate.now().minusDays(2);

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel com a data inicial menor que a data atual.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_com_valor_do_aluguel_nulo() {
        Double valorDoAluguel = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel com valor do aluguel vazio.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_com_valor_do_aluguel_igual_ou_menor_zero() {
        Double valorDoAluguel = -2.0;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro, valorDoAluguel);
        });

        assertEquals("Não é possível criar um aluguel com valor do aluguel igual ou menor que zero.", excecaoDeDominio.getMessage());
    }
}