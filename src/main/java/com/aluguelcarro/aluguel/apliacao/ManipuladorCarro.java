package com.aluguelcarro.aluguel.apliacao;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManipuladorCarro {

    private CarroRepositorio carroRepositorio;

    @Autowired
    public ManipuladorCarro(CarroRepositorio carroRepositorio) {
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

    public List<Carro> listarTodosCarros(){
        return carroRepositorio.findAll();
    }
}
