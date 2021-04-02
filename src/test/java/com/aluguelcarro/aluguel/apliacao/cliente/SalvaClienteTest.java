package com.aluguelcarro.aluguel.apliacao.cliente;

import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.builder.ClienteBuilder;
import com.aluguelcarro.aluguel.dominio.builder.ClienteDtoBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SalvaClienteTest {

    private SalvaCliente salvaCliente;
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    void init() {
        clienteRepositorio = mock(ClienteRepositorio.class);
        salvaCliente = new SalvaCliente(clienteRepositorio);
    }

    @Test
    void deve_salvar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.save(Mockito.any())).thenReturn(new ClienteBuilder().criar());

        salvaCliente.salvar(clienteDto);

        verify(clienteRepositorio, times(1)).save(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_salvar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.save(Mockito.any())).thenReturn(new ClienteBuilder().criar());

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                salvaCliente.salvar(clienteDto);

        assertEquals("Não foi possível salvar os dados do cliente.", respostaManipuladorClienteDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_salvar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        Mockito.when(clienteRepositorio.save(Mockito.any())).thenReturn(new ClienteBuilder().comId(1L).criarComId());

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                salvaCliente.salvar(clienteDto);

        assertEquals("Cliente salvo com sucesso!", respostaManipuladorClienteDto.mensagemDeResposta);
    }

}