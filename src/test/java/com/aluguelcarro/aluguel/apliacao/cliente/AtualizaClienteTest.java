package com.aluguelcarro.aluguel.apliacao.cliente;

import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.builder.ClienteBuilder;
import com.aluguelcarro.aluguel.dominio.builder.ClienteDtoBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtualizaClienteTest {

    private AtualizaCliente atualizaCliente;
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    void init() {
        clienteRepositorio = mock(ClienteRepositorio.class);
        atualizaCliente = new AtualizaCliente(clienteRepositorio);
    }

    @Test
    void deve_atualizar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()));

        atualizaCliente.atualizar(clienteDto);
        ArgumentCaptor<Cliente> argumentCaptor = ArgumentCaptor
                .forClass(Cliente.class);
        verify(clienteRepositorio).save(argumentCaptor.capture());
        String nomeDoClienteAtualizado = argumentCaptor.getValue()
                .getNomeCompleto();

        verify(clienteRepositorio, times(1)).save(Mockito.any());
        assertEquals(clienteDto.nomeCompleto, nomeDoClienteAtualizado);
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_encontrar_cliente_para_atualizar() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.empty());

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                atualizaCliente.atualizar(clienteDto);

        assertEquals("Não foi possível encontrar um cliente para atualizar.",
                respostaManipuladorClienteDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_atualizar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(
                new ClienteBuilder().comId(1L).criarComId()));

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                atualizaCliente.atualizar(clienteDto);

        assertEquals("Cliente atualizado com sucesso!",
                respostaManipuladorClienteDto.mensagemDeResposta);
    }

}