package com.aluguelcarro.aluguel.apliacao.carro;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Component
@Transactional(rollbackFor = {SQLException.class})
public class DeletaCarro {

    private CarroRepositorio carroRepositorio;

    @Autowired
    public DeletaCarro(CarroRepositorio carroRepositorio) {
        this.carroRepositorio = carroRepositorio;
    }

    public RespostaManipuladorCarroDto deletar(CarroDto carroDto) {

        Optional<Carro> carro = carroRepositorio.findById(carroDto.id);

        if(carro.isEmpty()){
            return new RespostaManipuladorCarroDto("Não foi possível encontrar o carro escolhido para deletar.");
        }

        carroRepositorio.delete(carro.get());

        Optional<Carro> carroEncontrado = carroRepositorio.findById(carroDto.id);

        if(carroEncontrado.isPresent()){
            return new RespostaManipuladorCarroDto("Não foi possível deletar o carro selecionado.");
        }

        return new RespostaManipuladorCarroDto("Carro deletado com sucesso!");
    }

}
