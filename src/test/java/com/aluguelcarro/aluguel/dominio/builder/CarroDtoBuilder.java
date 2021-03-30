package com.aluguelcarro.aluguel.dominio.builder;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;

public class CarroDtoBuilder {

    private Carro carro;

    public CarroDtoBuilder(){
        carro = new CarroBuilder().criar();
    }

    public CarroDto criar(){
        CarroDto carroDto = new CarroDto();

        carroDto.marca = carro.getMarca();
        carroDto.modelo = carro.getModelo();
        carroDto.renavam = carro.getRenavam();
        carroDto.valorDiaria = carro.getValorDiaria();
        carroDto.id = 1L;

        return carroDto;
    }
}
