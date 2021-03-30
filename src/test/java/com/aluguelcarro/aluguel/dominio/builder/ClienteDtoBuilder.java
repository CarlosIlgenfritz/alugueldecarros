package com.aluguelcarro.aluguel.dominio.builder;

import com.aluguelcarro.aluguel.apliacao.dtos.ClienteDto;
import com.aluguelcarro.aluguel.dominio.Cliente;

public class ClienteDtoBuilder {

    private Cliente cliente;

    public ClienteDtoBuilder(){
        cliente = new ClienteBuilder().criar();
    }

    public ClienteDto criar(){
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.nomeCompleto = cliente.getNomeCompleto();
        clienteDto.cpf = cliente.getCpf();
        clienteDto.email = cliente.getEmail();
        clienteDto.id = 1L;

        return  clienteDto;
    }
}
