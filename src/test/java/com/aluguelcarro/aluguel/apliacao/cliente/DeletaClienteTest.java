package com.aluguelcarro.aluguel.apliacao.cliente;

import com.aluguelcarro.aluguel.apliacao.ManipuladorCliente;
import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.builder.ClienteBuilder;
import com.aluguelcarro.aluguel.dominio.builder.ClienteDtoBuilder;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class DeletaClienteTest {

    private DeletaCliente deletaCliente;
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    void init() {
        clienteRepositorio = mock(ClienteRepositorio.class);
        deletaCliente = new DeletaCliente(clienteRepositorio);
    }

    @Test
    void deve_deletar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()));

        deletaCliente.deletar(clienteDto);

        verify(clienteRepositorio, times(1)).delete(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_encontrar_um_cliente_para_deletar() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.empty());

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                deletaCliente.deletar(clienteDto);

        assertEquals("Não foi possível encontrar um cliente selecionado para deletar.", respostaManipuladorClienteDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_encontrar_um_cliente_para_deletar() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()),
                Optional.empty());

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                deletaCliente.deletar(clienteDto);

        assertEquals("Cliente deletado com sucesso!", respostaManipuladorClienteDto.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_deletar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id))
                .thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()),
                        Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()));

        RespostaManipuladorClienteDto respostaManipuladorClienteDto =
                deletaCliente.deletar(clienteDto);

        assertEquals("Não foi possível deletar o cliente selecionado.", respostaManipuladorClienteDto.mensagemDeResposta);
    }
}