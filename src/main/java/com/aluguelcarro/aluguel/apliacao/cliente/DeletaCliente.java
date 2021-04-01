package com.aluguelcarro.aluguel.apliacao.cliente;

import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Component
@Transactional(rollbackFor = {SQLException.class})
public class DeletaCliente {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public DeletaCliente(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public RespostaManipuladorClienteDto deletar(ClienteDto clienteDto) {

        Optional<Cliente> clienteOptional = clienteRepositorio.findById(clienteDto.id);

        if(clienteOptional.isEmpty()){
            return new RespostaManipuladorClienteDto("Não foi possível encontrar um cliente selecionado para deletar.");
        }

        clienteRepositorio.delete(clienteOptional.get());

        Optional<Cliente> clienteEncontrado = clienteRepositorio.findById(clienteDto.id);

        if(clienteEncontrado.isPresent()){
            return new RespostaManipuladorClienteDto("Não foi possível deletar o cliente selecionado.");
        }

        return new RespostaManipuladorClienteDto("Cliente deletado com sucesso!");
    }

}
