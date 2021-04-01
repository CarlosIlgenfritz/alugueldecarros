package com.aluguelcarro.aluguel.apliacao.carro;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Objects;

@Component
@Transactional(rollbackFor = {SQLException.class})
public class SalvaCarro {

    private CarroRepositorio carroRepositorio;

    @Autowired
    public SalvaCarro(CarroRepositorio carroRepositorio) {
        this.carroRepositorio = carroRepositorio;
    }

    public RespostaManipuladorCarroDto salvar(CarroDto carroDto){

        Carro carro = new Carro(carroDto.modelo, carroDto.marca,
                carroDto.renavam, carroDto.valorDiaria);

        Carro carroSalvo = carroRepositorio.save(carro);

        if(Objects.isNull(carroSalvo.getId())){
            return new RespostaManipuladorCarroDto("Não foi possível salvar o carro.");
        }

        return new RespostaManipuladorCarroDto("Carro salvo com sucesso!");
    }

}
