package com.aluguelcarro.aluguel.apliacao;

import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.builder.CarroBuilder;
import com.aluguelcarro.aluguel.dominio.builder.ClienteBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.AluguelRepositorio;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RealizaAluguelTest {

    private Faker faker;
    private RealizaAluguel realizaAluguel;
    private AluguelRepositorio aluguelRepositorio;

    @BeforeEach
    void init(){
        faker = new Faker();
        realizaAluguel = new RealizaAluguel();
    }

    @Test
    void deve_salvar_um_aluguel(){
        aluguelRepositorio = Mockito.mock(AluguelRepositorio.class);
        AlugarDto alugarDto = criarAlugarDto();

        realizaAluguel.alugar(alugarDto);

        verify(aluguelRepositorio, times(1)).save(Mockito.any());
    }

    private AlugarDto criarAlugarDto(){
        AlugarDto alugarDto = new AlugarDto();

        alugarDto.dataInicio = LocalDate.now();
        alugarDto.dataFim = LocalDate.now().plusDays(10);
        alugarDto.carro = criarCarroDto();
        alugarDto.cliente = criarClienteDto();

        return alugarDto;
    }

    private ClienteDto criarClienteDto() {
        Cliente cliente = new ClienteBuilder().criar();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.cpf = cliente.getCpf();
        clienteDto.email = cliente.getEmail();
        clienteDto.nomeCompleto = cliente.getNomeCompleto();

        return clienteDto;
    }

    private CarroDto criarCarroDto() {
        CarroDto carroDto = new CarroDto();
        Carro carro = new CarroBuilder().criar();

        carroDto.marca = carro.getMarca();
        carroDto.modelo = carro.getModelo();
        carroDto.renavam = carro.getRenavam();
        carroDto.valorDiaria = carro.getValorDiaria();

        return carroDto;
    }
}