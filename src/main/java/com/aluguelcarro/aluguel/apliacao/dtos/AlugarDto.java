package com.aluguelcarro.aluguel.apliacao.dtos;
import java.time.LocalDate;

public class AlugarDto {

    public LocalDate dataInicio;
    public LocalDate dataFim;
    public Long clienteId;
    public Long carroId;
    public Double valorDoAluguel;
}
