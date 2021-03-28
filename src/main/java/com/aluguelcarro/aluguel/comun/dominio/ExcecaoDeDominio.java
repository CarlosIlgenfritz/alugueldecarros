package com.aluguelcarro.aluguel.comun.dominio;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ExcecaoDeDominio extends RuntimeException {

	private static final long serialVersionUID = 5209733260304982307L;

	private ExcecaoDeDominio(String mensagemDeErro) { 
        super(mensagemDeErro);
    }

    public static void quandoTextoVazioOuNulo(String texto, String mensagemDeErro) {
    	if(StringUtils.isEmpty(texto)) {
    		entaoDisparar(mensagemDeErro);
    	} else if(StringUtils.isEmpty(texto.trim())) {
            entaoDisparar(mensagemDeErro);
        }
    }

    public static void quandoValorIgualAZero(int valor, String mensagemDeErro) {
    	if(valor == 0) {
    		entaoDisparar(mensagemDeErro);
    	}
    }

    public static void quandoValorMenorIgualZero(Double valor, String mensagemDeErro) {

        if(valor.intValue() <= 0) {
            entaoDisparar(mensagemDeErro);
        }
    }

    public static void quandoValorIgualAZero(long valor, String mensagemDeErro) {
        if(valor == 0) {
            entaoDisparar(mensagemDeErro);
        }
    }

    public static void quandoValorMenorQueUm(BigDecimal valor, String mensagemDeErro) {
        if(valor.intValue() < 1) {
            entaoDisparar(mensagemDeErro);
        }
    }

    public static void quandoTamanoDiferenteDeOnze(String valor, String mensagemDeErro) {
        if(valor.length() != 11) {
            entaoDisparar(mensagemDeErro);
        }
    }
    
    public static void quandoNulo(Object objeto, String mensagemDeErro) {
        if(Objects.isNull(objeto)) {
            entaoDisparar(mensagemDeErro);
        }
	}
    
    public static void quandoListaNulaOuVazia(List<?> lista, String mensagemDeErro) {
    	if(Objects.isNull(lista)) {
    		entaoDisparar(mensagemDeErro);
    	}else if(lista.isEmpty())
    		entaoDisparar(mensagemDeErro);
    }
    
    private static void entaoDisparar(String mensagemDeErro) {
        throw new ExcecaoDeDominio(mensagemDeErro);
    }

    public static void quandoVerdadeiro(boolean verdadeiro, String mensagemDeErro) {
	    if(verdadeiro){
	        entaoDisparar(mensagemDeErro);
        }
    }
}