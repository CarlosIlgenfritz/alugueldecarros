package com.aluguelcarro.aluguel.portaadaptador.rest;

import com.aluguelcarro.aluguel.apliacao.ManipuladorCliente;
import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorClienteDto;
import com.aluguelcarro.aluguel.dominio.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {

    @Autowired
    private ManipuladorCliente manipuladorCliente;

    @GetMapping
    public List<Cliente> buscarTodosOsClientes(){
        return manipuladorCliente.buscarTodosOsclientes();
    }

    @PostMapping
    public RespostaManipuladorClienteDto salvarCliente(@RequestBody ClienteDto clienteDto){
        return manipuladorCliente.salvar(clienteDto);
    }

    @PutMapping
    public RespostaManipuladorClienteDto atualizarCliente(@RequestBody ClienteDto clienteDto){
        return manipuladorCliente.atualizar(clienteDto);
    }

    @DeleteMapping
    public RespostaManipuladorClienteDto deletarCliente(@RequestBody ClienteDto clienteDto){
        return manipuladorCliente.deletar(clienteDto);
    }
}
