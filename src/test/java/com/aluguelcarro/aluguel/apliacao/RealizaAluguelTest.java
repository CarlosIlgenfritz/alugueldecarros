package com.aluguelcarro.aluguel.apliacao;

import com.aluguelcarro.aluguel.apliacao.dtos.AlugarDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaRealizaAluguelDto;
import com.aluguelcarro.aluguel.dominio.builder.CarroBuilder;
import com.aluguelcarro.aluguel.dominio.builder.ClienteBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.AluguelRepositorio;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RealizaAluguelTest {

    private Faker faker;
    private RealizaAluguel realizaAluguel;
    private AluguelRepositorio aluguelRepositorio;
    private ClienteRepositorio clienteRepositorio;
    private CarroRepositorio carroRepositorio;

    @BeforeEach
    void init() {
        clienteRepositorio = Mockito.mock(ClienteRepositorio.class);
        carroRepositorio = Mockito.mock(CarroRepositorio.class);
        aluguelRepositorio = Mockito.mock(AluguelRepositorio.class);

        faker = new Faker();
        realizaAluguel = new RealizaAluguel(clienteRepositorio, carroRepositorio, aluguelRepositorio);
    }

    @Test
    void deve_salvar_um_aluguel() {
        AlugarDto alugarDto = criarAlugarDto();
        Mockito.when(clienteRepositorio.findById(alugarDto.clienteId)).thenReturn(Optional.ofNullable(new ClienteBuilder().criar()));
        Mockito.when(carroRepositorio.findById(alugarDto.carroId)).thenReturn(Optional.ofNullable(new CarroBuilder().criar()));

        realizaAluguel.alugar(alugarDto);

        verify(aluguelRepositorio, times(1)).save(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_encontrar_um_carro() {
        AlugarDto alugarDto = criarAlugarDto();
        Mockito.when(clienteRepositorio.findById(alugarDto.clienteId)).thenReturn(Optional.ofNullable(new ClienteBuilder().criar()));
        Mockito.when(carroRepositorio.findById(alugarDto.carroId)).thenReturn(Optional.empty());

        RespostaRealizaAluguelDto respostaRealizaAluguelDto = realizaAluguel.alugar(alugarDto);

        assertEquals("Não foi possível encontrar o veículo escolhido.", respostaRealizaAluguelDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_encontrar_um_cliente() {
        AlugarDto alugarDto = criarAlugarDto();
        Mockito.when(clienteRepositorio.findById(alugarDto.clienteId)).thenReturn(Optional.empty());
        Mockito.when(carroRepositorio.findById(alugarDto.carroId)).thenReturn(Optional.ofNullable(new CarroBuilder().criar()));

        RespostaRealizaAluguelDto respostaRealizaAluguelDto = realizaAluguel.alugar(alugarDto);

        assertEquals("Não foi possível encontrar o cliente escolhido.", respostaRealizaAluguelDto.mensagemDeResposta);
    }

    private AlugarDto criarAlugarDto() {
        AlugarDto alugarDto = new AlugarDto();

        alugarDto.dataInicio = LocalDate.now();
        alugarDto.dataFim = LocalDate.now().plusDays(10);
        alugarDto.carroId = 1L;
        alugarDto.clienteId = 2L;
        alugarDto.valorDoAluguel = faker.number().randomDouble(1, 1,10);
        return alugarDto;
    }


}