package com.aluguelcarro.aluguel.apliacao;

import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManipuladorCliente {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public ManipuladorCliente(ClienteRepositorio clienteRepositorio) {
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

    public List<Cliente> buscarTodosOsclientes() {
        return clienteRepositorio.findAll();
    }
}
