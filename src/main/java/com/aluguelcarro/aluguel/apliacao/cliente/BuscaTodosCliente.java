package com.aluguelcarro.aluguel.apliacao.cliente;

import com.aluguelcarro.aluguel.dominio.Cliente;
import com.aluguelcarro.aluguel.dominio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@Transactional(rollbackFor = {SQLException.class}, readOnly = true)
public class BuscaTodosCliente {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public BuscaTodosCliente(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public List<Cliente> buscarTodosOsclientes() {
        return clienteRepositorio.findAll();
    }

}
