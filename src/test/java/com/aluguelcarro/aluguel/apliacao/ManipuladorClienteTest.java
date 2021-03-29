package com.aluguelcarro.aluguel.apliacao;

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

public class ManipuladorClienteTest {

    private ManipuladorCliente manipuladorCliente;
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    void init() {
        clienteRepositorio = mock(ClienteRepositorio.class);
        manipuladorCliente = new ManipuladorCliente(clienteRepositorio);
    }

    @Test
    void deve_salvar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.save(Mockito.any())).thenReturn(new ClienteBuilder().criar());

        manipuladorCliente.salvar(clienteDto);

        verify(clienteRepositorio, times(1)).save(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_salvar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.save(Mockito.any())).thenReturn(new ClienteBuilder().criar());

        RespostaManipuladorCliente respostaManipuladorCliente =
                manipuladorCliente.salvar(clienteDto);

        assertEquals("Não foi possível salvar os dados do cliente.", respostaManipuladorCliente.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_salvar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        Mockito.when(clienteRepositorio.save(Mockito.any())).thenReturn(new ClienteBuilder().comId(1L).criarComId());

        RespostaManipuladorCliente respostaManipuladorCliente =
                manipuladorCliente.salvar(clienteDto);

        assertEquals("Cliente salvo com sucesso!", respostaManipuladorCliente.mensagemDeResposta);
    }

    @Test
    void deve_atualizar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()));

        manipuladorCliente.atualizar(clienteDto);
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

        RespostaManipuladorCliente respostaManipuladorCliente =
                manipuladorCliente.atualizar(clienteDto);

        assertEquals("Não foi possível encontrar um cliente para atualizar.",
                respostaManipuladorCliente.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_atualizar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(
                new ClienteBuilder().comId(1L).criarComId()));

        RespostaManipuladorCliente respostaManipuladorCliente =
                manipuladorCliente.atualizar(clienteDto);

        assertEquals("Cliente atualizado com sucesso!",
                respostaManipuladorCliente.mensagemDeResposta);
    }

    @Test
    void deve_deletar_um_cliente() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()));

        manipuladorCliente.deletar(clienteDto);

        verify(clienteRepositorio, times(1)).delete(Mockito.any());
    }

    @Test
    void deve_retornar_mensagem_de_erro_quando_nao_conseguir_encontrar_um_cliente_para_deletar() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.empty());

        RespostaManipuladorCliente respostaManipuladorCliente =
                manipuladorCliente.deletar(clienteDto);

        assertEquals("Não foi possível encontrar um cliente selecionado para deletar.", respostaManipuladorCliente.mensagemDeResposta);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_conseguir_encontrar_um_cliente_para_deletar() {
        ClienteDto clienteDto = new ClienteDtoBuilder().criar();
        when(clienteRepositorio.findById(clienteDto.id)).thenReturn(Optional.ofNullable(new ClienteBuilder().comId(1L).criarComId()));

        RespostaManipuladorCliente respostaManipuladorCliente =
                manipuladorCliente.deletar(clienteDto);

        assertEquals("Cliente deletado com sucesso!", respostaManipuladorCliente.mensagemDeResposta);
    }

}
