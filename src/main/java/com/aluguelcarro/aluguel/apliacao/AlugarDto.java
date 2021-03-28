package com.aluguelcarro.aluguel.apliacao;

import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.Cliente;
import java.time.LocalDate;

public class AlugarDto {

    public LocalDate dataInicio;
    public LocalDate dataFim;
    public ClienteDto cliente;
    public CarroDto carro;
}
