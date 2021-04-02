package com.aluguelcarro.aluguel.portaadaptador.rest;


import com.aluguelcarro.aluguel.apliacao.carro.AtualizaCarro;
import com.aluguelcarro.aluguel.apliacao.carro.BuscaTodosCarros;
import com.aluguelcarro.aluguel.apliacao.carro.DeletaCarro;
import com.aluguelcarro.aluguel.apliacao.carro.SalvaCarro;
import com.aluguelcarro.aluguel.apliacao.dtos.CarroDto;
import com.aluguelcarro.aluguel.apliacao.dtos.RespostaManipuladorCarroDto;
import com.aluguelcarro.aluguel.dominio.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroRest {

    @Autowired
    private SalvaCarro salvaCarro;

    @Autowired
    private AtualizaCarro atualizaCarro;

    @Autowired
    private DeletaCarro deletaCarro;

    @Autowired
    private BuscaTodosCarros buscarTodosOsCarros;

    @GetMapping
    public List<Carro> buscarTodosOsCarros(){
        return buscarTodosOsCarros.buscarTodosOsCarros();
    }

    @PostMapping
    public RespostaManipuladorCarroDto salvarCarro(@RequestBody CarroDto carroDto){
        return salvaCarro.salvar(carroDto);
    }

    @PutMapping
    public RespostaManipuladorCarroDto atualizaCarro(@RequestBody CarroDto carroDto){
        return atualizaCarro.atualizar(carroDto);
    }

    @DeleteMapping
    public RespostaManipuladorCarroDto deletaCarro(@RequestBody CarroDto carroDto){
        return deletaCarro.deletar(carroDto);
    }
}
