package com.aluguelcarro.aluguel.apliacao.dtos;

public class RespostaRealizaAluguelDto {

    public String mensagemDeResposta;

    public RespostaRealizaAluguelDto(String mensagemDeErro) {
        this.mensagemDeResposta = mensagemDeErro;
    }
}
