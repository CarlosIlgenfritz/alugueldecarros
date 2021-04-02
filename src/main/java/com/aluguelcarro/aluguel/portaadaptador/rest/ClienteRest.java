package com.aluguelcarro.aluguel.portaadaptador.rest;

import com.aluguelcarro.aluguel.apliacao.cliente.AtualizaCliente;
import com.aluguelcarro.aluguel.apliacao.cliente.BuscaTodosCliente;
import com.aluguelcarro.aluguel.apliacao.cliente.DeletaCliente;
import com.aluguelcarro.aluguel.apliacao.cliente.SalvaCliente;
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
    private SalvaCliente salvaCliente;

    @Autowired
    private AtualizaCliente atualizaCliente;

    @Autowired
    private DeletaCliente deletaCliente;

    @Autowired
    private BuscaTodosCliente buscarTodosOsclientes;

    @GetMapping
    public List<Cliente> buscarTodosOsClientes(){
        return buscarTodosOsclientes.buscarTodosOsclientes();
    }

    @PostMapping
    public RespostaManipuladorClienteDto salvarCliente(@RequestBody ClienteDto clienteDto){
        return salvaCliente.salvar(clienteDto);
    }

    @PutMapping
    public RespostaManipuladorClienteDto atualizarCliente(@RequestBody ClienteDto clienteDto){
        return atualizaCliente.atualizar(clienteDto);
    }

    @DeleteMapping
    public RespostaManipuladorClienteDto deletarCliente(@RequestBody ClienteDto clienteDto){
        return deletaCliente.deletar(clienteDto);
    }
}
