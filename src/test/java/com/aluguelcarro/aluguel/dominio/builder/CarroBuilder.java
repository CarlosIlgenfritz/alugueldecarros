package com.aluguelcarro.aluguel.dominio.builder;

import com.aluguelcarro.aluguel.dominio.Carro;
import com.github.javafaker.Faker;

import java.lang.reflect.Field;

public class CarroBuilder {

    private Long id;
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

    public CarroBuilder comId(Long id){
        this.id = id;
        return this;
    }

    public Carro criar() {
        return new Carro(modelo, marca, renavam, valorDiaria);
    }

    public Carro criarComId() {
        Carro carro = new Carro(modelo, marca, renavam, valorDiaria);

        try {
            Field idField = carro.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(carro, id);
            idField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return carro;
    }
}