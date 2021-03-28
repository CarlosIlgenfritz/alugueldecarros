package com.aluguelcarro.aluguel.portaadaptador.rest;


import com.aluguelcarro.aluguel.apliacao.AlugarDto;
import com.aluguelcarro.aluguel.apliacao.RealizaAluguel;
import com.aluguelcarro.aluguel.apliacao.RespostaRealizaAluguelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realizaaluguel")
public class RealizaAluguelRest {

    @Autowired
    private RealizaAluguel realizaAluguel;


    @PostMapping
    public RespostaRealizaAluguelDto realizarAlguel(@RequestBody AlugarDto alugarDto){
        return realizaAluguel.alugar(alugarDto);
    }
}
