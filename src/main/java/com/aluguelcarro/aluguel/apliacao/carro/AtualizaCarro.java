package com.aluguelcarro.aluguel.apliacao.carro;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {SQLException.class})
public class AtualizaCarro {

    private CarroRepositorio carroRepositorio;

    @Autowired
    public AtualizaCarro(CarroRepositorio carroRepositorio) {
        this.carroRepositorio = carroRepositorio;
    }

    public RespostaManipuladorCarroDto atualizar(CarroDto carroDto) {

        Optional<Carro> carro = carroRepositorio.findById(carroDto.id);

        if(carro.isEmpty()){
            return new RespostaManipuladorCarroDto("Não foi possível encontrar o carro para atualizar.");
        }

        Carro carroEncontrado = carro.get();

        Carro carroAtualizado = new Carro(carroDto.modelo,
                carroDto.marca,carroDto.renavam,carroDto.valorDiaria);
        carroAtualizado.informarId(carroEncontrado.getId());

        carroRepositorio.save(carroAtualizado);

        return new RespostaManipuladorCarroDto("Carro atualizado com sucesso!");
    }
}
