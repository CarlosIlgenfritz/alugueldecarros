package com.aluguelcarro.aluguel.apliacao;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ManipuladorCarroTest {

    private CarroRepositorio carroRepositorio;

    private ManipuladorCarro manipuladorCarro;

    @BeforeEach
    void init(){
        carroRepositorio = mock(CarroRepositorio.class);
        manipuladorCarro = new ManipuladorCarro(carroRepositorio);
    }
    @Test
    void deve_salvar_um_veiculo(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().criar();
        when(carroRepositorio.save(Mockito.any())).thenReturn(carro);

        manipuladorCarro.salvar(carroDto);

        verify(carroRepositorio, times(1)).save(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_salvar_um_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().comId(1L).criarComId();
        when(carroRepositorio.save(Mockito.any())).thenReturn(carro);

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                manipuladorCarro.salvar(carroDto);

        assertEquals("Carro salvo com sucesso!", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_salvar_um_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().criar();
        when(carroRepositorio.save(Mockito.any())).thenReturn(carro);

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                manipuladorCarro.salvar(carroDto);

        assertEquals("Não foi possível salvar o carro.", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_atualizar_os_dados_de_um_carro_que_ja_existe(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()));

        manipuladorCarro.atualizar(carroDto);
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
                manipuladorCarro.atualizar(carroDto);

        assertEquals("Não foi possível encontrar o carro para atualizar.",
                respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_atualizar(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()));

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                manipuladorCarro.atualizar(carroDto);

        assertEquals("Carro atualizado com sucesso!", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_deletar_um_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().comId(1L).criarComId();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(carro));

        manipuladorCarro.deletar(carroDto);

        verify(carroRepositorio, times(1)).delete(carro);
    }

    @Test
    void deve_enviar_mensagem_de_sucesso_ao_deletar_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()),
                        Optional.empty());

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                manipuladorCarro.deletar(carroDto);

        assertEquals("Carro deletado com sucesso!", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_enviar_mensagem_de_erro_ao_nao_encontrar_carro_para_deletar(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.empty());

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                manipuladorCarro.deletar(carroDto);

        assertEquals("Não foi possível encontrar o carro escolhido para deletar.", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_enviar_mensagem_de_erro_ao_nao_deletar_carro_selecionado(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        when(carroRepositorio.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()),
                        Optional.ofNullable(new CarroBuilder().comId(1L).criarComId()));

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                manipuladorCarro.deletar(carroDto);

        assertEquals("Não foi possível deletar o carro selecionado.", respostaManipuladorCarroDto.mensagemDeResposta);
    }

}