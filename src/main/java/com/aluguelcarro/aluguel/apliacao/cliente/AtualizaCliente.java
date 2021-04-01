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
public class AtualizaCliente {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public AtualizaCliente(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public RespostaManipuladorClienteDto atualizar(ClienteDto clienteDto) {

        Optional<Cliente> clienteOptional = clienteRepositorio.findById(clienteDto.id);

        if(clienteOptional.isEmpty()){
            return new RespostaManipuladorClienteDto("Não foi possível encontrar um cliente para atualizar.");
        }

        Cliente clienteEncontrado = clienteOptional.get();

        Cliente clienteAtualizado = new Cliente(clienteDto.nomeCompleto, clienteDto.email,
                clienteDto.cpf);
        clienteAtualizado.informarId(clienteEncontrado.getId());

        clienteRepositorio.save(clienteAtualizado);

        return new RespostaManipuladorClienteDto("Cliente atualizado com sucesso!");
    }
}
