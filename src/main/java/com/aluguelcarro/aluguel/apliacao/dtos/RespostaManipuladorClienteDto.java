package com.aluguelcarro.aluguel.apliacao.dtos;

public class RespostaManipuladorClienteDto {

    public String mensagemDeResposta;

    public RespostaManipuladorClienteDto(String mensagemDeErro) {
        this.mensagemDeResposta = mensagemDeErro;
    }
}
