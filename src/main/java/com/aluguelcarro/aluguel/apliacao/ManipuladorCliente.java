package com.aluguelcarro.aluguel.apliacao;

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

    public RespostaManipuladorCliente salvar(ClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto.nomeCompleto, clienteDto.email, clienteDto.cpf);

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        if (clienteSalvo == null || clienteSalvo.getId() == null) {
            return new RespostaManipuladorCliente("Não foi possível salvar os dados do cliente.");
        }

        return new RespostaManipuladorCliente("Cliente salvo com sucesso!");
    }

    public RespostaManipuladorCliente atualizar(ClienteDto clienteDto) {

        Optional<Cliente> clienteOptional = clienteRepositorio.findById(clienteDto.id);

        if(clienteOptional.isEmpty()){
            return new RespostaManipuladorCliente("Não foi possível encontrar um cliente para atualizar.");
        }

        Cliente clienteEncontrado = clienteOptional.get();

        Cliente clienteAtualizado = new Cliente(clienteDto.nomeCompleto, clienteDto.email,
                clienteDto.cpf);
        clienteAtualizado.informarId(clienteEncontrado.getId());

        clienteRepositorio.save(clienteAtualizado);

        return new RespostaManipuladorCliente("Cliente atualizado com sucesso!");
    }

    public RespostaManipuladorCliente deletar(ClienteDto clienteDto) {

        Optional<Cliente> clienteOptional = clienteRepositorio.findById(clienteDto.id);

        if(clienteOptional.isEmpty()){
            return new RespostaManipuladorCliente("Não foi possível encontrar um cliente selecionado para deletar.");
        }

        clienteRepositorio.delete(clienteOptional.get());

        return new RespostaManipuladorCliente("Cliente deletado com sucesso!");
    }

    public List<Cliente> buscarTodosOsclientes() {

        return clienteRepositorio.findAll();
    }
}
