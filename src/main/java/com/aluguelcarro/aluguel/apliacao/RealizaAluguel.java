package com.aluguelcarro.aluguel.apliacao;

import com.aluguelcarro.aluguel.apliacao.dtos.AlugarDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaRealizaAluguelDto;
import com.aluguelcarro.aluguel.dominio.Aluguel;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.repositorios.AluguelRepositorio;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealizaAluguel {

    private ClienteRepositorio clienteRepositorio;
    private CarroRepositorio carroRepositorio;
    private AluguelRepositorio aluguelRepositorio;

    @Autowired
    public RealizaAluguel(ClienteRepositorio clienteRepositorio, CarroRepositorio carroRepositorio,
                          AluguelRepositorio aluguelRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
        this.carroRepositorio = carroRepositorio;
        this.aluguelRepositorio = aluguelRepositorio;
    }

    public RespostaRealizaAluguelDto alugar(AlugarDto alugarDto) {
        Optional<Carro> carro = carroRepositorio.findById(alugarDto.carroId);
        if(carro.isEmpty()){
            return new RespostaRealizaAluguelDto("Não foi possível encontrar o veículo escolhido.");
        }

        Optional<Cliente> cliente = clienteRepositorio.findById(alugarDto.clienteId);
        if(cliente.isEmpty()){
            return new RespostaRealizaAluguelDto("Não foi possível encontrar o cliente escolhido.");
        }

        Aluguel aluguel = new Aluguel(alugarDto.dataInicio, alugarDto.dataFim, alugarDto.clienteId, alugarDto.carroId);

        aluguelRepositorio.save(aluguel);

        return new RespostaRealizaAluguelDto("Aluguel realizado com sucesso!");
    }
}
