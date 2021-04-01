package com.aluguelcarro.aluguel.apliacao.cliente;

import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Component
@Transactional(rollbackFor = {SQLException.class})
public class SalvaCliente{

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public SalvaCliente(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public RespostaManipuladorClienteDto salvar(ClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto.nomeCompleto, clienteDto.email, clienteDto.cpf);

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        if (clienteSalvo == null || clienteSalvo.getId() == null) {
            return new RespostaManipuladorClienteDto("Não foi possível salvar os dados do cliente.");
        }

        return new RespostaManipuladorClienteDto("Cliente salvo com sucesso!");
    }

}
