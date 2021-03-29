package com.aluguelcarro.aluguel.dominio;

import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import com.aluguelcarro.aluguel.dominio.builder.CarroBuilder;
import com.aluguelcarro.aluguel.dominio.builder.ClienteBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AluguelTest {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Cliente cliente;
    private Carro carro;

    @BeforeEach
    void init() {
        dataInicio = LocalDate.now();
        dataFim = LocalDate.now().plusDays(2);
        cliente = new ClienteBuilder().criar();
        carro = new CarroBuilder().criar();
    }

    @Test
    void deve_instanciar_aluguel() {

        Aluguel aluguel = new Aluguel(dataInicio, dataFim, cliente, carro);

        assertEquals(dataInicio, aluguel.getDataInicio());
        assertEquals(dataFim, aluguel.getDataFim());
        assertEquals(cliente, aluguel.getCliente());
        assertEquals(carro, aluguel.getCarro());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_data_inicio() {
        LocalDate dataInicio = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro);
        });

        assertEquals("Não é possível criar um aluguel sem data de inicio.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_data_final() {
        LocalDate dataFim = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro);
        });

        assertEquals("Não é possível criar um aluguel sem data final.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_um_cliente() {
        Cliente cliente = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro);
        });

        assertEquals("Não é possível criar um aluguel sem um cliente.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_sem_um_carro() {
        Carro carro = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro);
        });

        assertEquals("Não é possível criar um aluguel sem um carro.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_com_data_inicio_menor_que_a_data_atual() {
        LocalDate dataInicio = LocalDate.now().minusDays(2);

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro);
        });

        assertEquals("Não é possível criar um aluguel com a data inicial menor que a data atual.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_instanciar_aluguel_com_data_inicio_menor_que_data_final() {
        LocalDate dataFim = LocalDate.now().minusDays(2);

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Aluguel(dataInicio, dataFim, cliente, carro);
        });

        assertEquals("Não é possível criar um aluguel com a data inicial menor que a data atual.", excecaoDeDominio.getMessage());
    }
}