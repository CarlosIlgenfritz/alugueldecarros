package com.aluguelcarro.aluguel.apliacao.carro;

import com.aluguelcarro.aluguel.dominio.Carro;
import com.aluguelcarro.aluguel.dominio.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@Transactional(rollbackFor = {SQLException.class}, readOnly = true)
public class BuscaTodosCarros {

    private CarroRepositorio carroRepositorio;

    @Autowired
    public BuscaTodosCarros(CarroRepositorio carroRepositorio) {
        this.carroRepositorio = carroRepositorio;
    }

    public List<Carro> buscarTodosOsCarros() {
        return carroRepositorio.findAll();
    }

}
