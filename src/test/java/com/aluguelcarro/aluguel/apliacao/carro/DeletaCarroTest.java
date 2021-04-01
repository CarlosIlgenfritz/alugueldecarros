package com.aluguelcarro.aluguel.apliacao.carro;

import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.builder.CarroBuilder;
import com.aluguelcarro.aluguel.dominio.builder.CarroDtoBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class DeletaCarroTest {

    private CarroRepositorio carroRepositorio;

    private DeletaCarro deletaCarro;

    @BeforeEach
    void init(){
        carroRepositorio = mock(CarroRepositorio.class);
        deletaCarro = new DeletaCarro(carroRepositorio);
    }

    @Test
    void deve_deletar_um_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().comId(1L).criarComId();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(carro));

        deletaCarro.deletar(carroDto);

        verify(carroRepositorio, times(1)).delete(carro);
    }

    @Test
    void deve_enviar_mensagem_de_sucesso_ao_deletar_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()),
                        Optional.empty());

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                deletaCarro.deletar(carroDto);

        assertEquals("Carro deletado com sucesso!", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_enviar_mensagem_de_erro_ao_nao_encontrar_carro_para_deletar(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.empty());

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                deletaCarro.deletar(carroDto);

        assertEquals("Não foi possível encontrar o carro escolhido para deletar.", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_enviar_mensagem_de_erro_ao_nao_deletar_carro_selecionado(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()),
                        Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()));

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                deletaCarro.deletar(carroDto);

        assertEquals("Não foi possível deletar o carro selecionado.", respostaManipuladorCarroDto.mensagemDeResposta);
    }

}