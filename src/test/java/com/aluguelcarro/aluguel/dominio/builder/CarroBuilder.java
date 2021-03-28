package com.aluguelcarro.aluguel.dominio.builder;

import com.aluguelcarro.aluguel.dominio.Carro;
import com.github.javafaker.Faker;

public class CarroBuilder {

    private String modelo;
    private String marca;
    private String renavam;
    private Double valorDiaria;
    private Faker faker;

    public CarroBuilder() {
        faker = new Faker();
        modelo = faker.witcher().character();
        marca = faker.witcher().character();
        renavam = faker.number().digits(11);
        valorDiaria = faker.number().randomDouble(1, 1, 500);
    }

    public Carro criar() {
        return new Carro(modelo, marca, renavam, valorDiaria);
    }
}