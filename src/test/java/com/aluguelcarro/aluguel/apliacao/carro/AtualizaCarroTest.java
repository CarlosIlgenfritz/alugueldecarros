package com.aluguelcarro.aluguel.apliacao.carro;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.builder.CarroBuilder;
import com.aluguelcarro.aluguel.dominio.builder.CarroDtoBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizaCarroTest {

    private CarroRepositorio carroRepositorio;

    private AtualizaCarro atualizaCarro;

    @BeforeEach
    void init(){
        carroRepositorio = mock(CarroRepositorio.class);
        atualizaCarro = new AtualizaCarro(carroRepositorio);
    }

    @Test
    void deve_atualizar_os_dados_de_um_carro_que_ja_existe(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()));

        atualizaCarro.atualizar(carroDto);
        ArgumentCaptor<Carro> argumentCaptor = ArgumentCaptor
                .forClass(Carro.class);
        verify(carroRepositorio).save(argumentCaptor.capture());
        String marcaDoCarroAtualizado = argumentCaptor.getValue()
                .getMarca();

        assertEquals(carroDto.marca, marcaDoCarroAtualizado);
    }

    @Test
    void deve_retornar_mensagem_quando_nao_conseguir_encontrar_carro_para_atualizar(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(carroDto.id)).thenReturn(Optional.empty());

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                atualizaCarro.atualizar(carroDto);

        assertEquals("Não foi possível encontrar o carro para atualizar.",
                respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_atualizar(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()));

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                atualizaCarro.atualizar(carroDto);

        assertEquals("Carro atualizado com sucesso!", respostaManipuladorCarroDto.mensagemDeResposta);
    }


}