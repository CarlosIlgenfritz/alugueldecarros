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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class SalvaCarroTest {

    private CarroRepositorio carroRepositorio;

    private SalvaCarro salvaCarro;

    @BeforeEach
    void init(){
        carroRepositorio = mock(CarroRepositorio.class);
        salvaCarro = new SalvaCarro(carroRepositorio);
    }

    @Test
    void deve_salvar_um_veiculo(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().criar();
        when(carroRepositorio.save(Mockito.any())).thenReturn(carro);

        salvaCarro.salvar(carroDto);

        verify(carroRepositorio, times(1)).save(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_salvar_um_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().comId(1L).criarComId();
        when(carroRepositorio.save(Mockito.any())).thenReturn(carro);

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                salvaCarro.salvar(carroDto);

        assertEquals("Carro salvo com sucesso!", respostaManipuladorCarroDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_salvar_um_carro(){
        CarroDto carroDto = new CarroDtoBuilder().criar();
        Carro carro = new CarroBuilder().criar();
        when(carroRepositorio.save(Mockito.any())).thenReturn(carro);

        RespostaManipuladorCarroDto respostaManipuladorCarroDto =
                salvaCarro.salvar(carroDto);

        assertEquals("Não foi possível salvar o carro.", respostaManipuladorCarroDto.mensagemDeResposta);
    }


}