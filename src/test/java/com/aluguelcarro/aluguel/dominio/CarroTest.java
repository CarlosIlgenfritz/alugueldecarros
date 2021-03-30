package com.aluguelcarro.aluguel.dominio;

import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import com.aluguelcarro.aluguel.dominio.builder.CarroBuilder;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarroTest {

    private Faker faker;
    private String modelo;
    private String marca;
    private String renavam;
    private Double valorDiaria;

    @BeforeEach
    void init() {
        faker = new Faker();
        modelo = faker.aviation().aircraft();
        marca = faker.aviation().airport();
        renavam = faker.number().digits(11);
        valorDiaria = faker.number().randomDouble(1, 1, 500);
    }

    @Test
    void deve_ser_possivel_instanciar_um_carro() {

        Carro carro = new Carro(modelo, marca, renavam, valorDiaria);

        assertEquals(modelo, carro.getModelo());
        assertEquals(marca, carro.getMarca());
        assertEquals(renavam, carro.getRenavam());
        assertEquals(valorDiaria, carro.getValorDiaria());

    }

    @Test
    void nao_deve_ser_possivel_instanciar_carro_sem_modelo() {
        String modelo = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Carro(modelo, marca, renavam, valorDiaria);
        });

        assertEquals("Não é possível criar um carro sem modelo.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_ser_possivel_instanciar_carro_sem_marca() {
        String marca = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Carro(modelo, marca, renavam, valorDiaria);
        });

        assertEquals("Não é possível criar um carro sem marca.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_ser_possivel_instanciar_carro_sem_renavam() {
        String renavam = null;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Carro(modelo, marca, renavam, valorDiaria);
        });

        assertEquals("Não é possível criar um carro sem renavam.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_ser_possivel_instanciar_carro_com_tamanho_do_renavam_diferente_de_onze() {
        String renavam = "0123";

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Carro(modelo, marca, renavam, valorDiaria);
        });

        assertEquals("O código renavam deve ter 11 digitos.", excecaoDeDominio.getMessage());
    }

    @Test
    void nao_deve_ser_possivel_instanciar_carro_com_valor_diaria_menor_ou_igual_a_zero() {
        Double valorDiaria = -2.0;

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            new Carro(modelo, marca, renavam, valorDiaria);
        });

        assertEquals("Não é possível criar um carro com valor da diária menor ou igual a zero.", excecaoDeDominio.getMessage());
    }

    @Test
    void deve_ser_possivel_informar_id(){
        Long id = 1L;
        Carro carro = new CarroBuilder().criar();

        carro.informarId(id);

        assertEquals(id, carro.getId());
    }

    @Test
    void nao_deve_ser_possivel_informar_id_nulo(){
        Long id = null;
        Carro carro = new CarroBuilder().criar();

        ExcecaoDeDominio excecaoDeDominio = assertThrows(ExcecaoDeDominio.class, () -> {
            carro.informarId(id);
        });

        assertEquals("Não é possível informar um id vazio para um carro.", excecaoDeDominio.getMessage());
    }
}