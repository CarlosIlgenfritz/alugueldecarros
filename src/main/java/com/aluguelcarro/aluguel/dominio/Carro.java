package com.aluguelcarro.aluguel.dominio;

import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "CARRO")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String modelo;

    @Column
    private String marca;

    @Column
    private String renavam;

    @Column
    private Double valorDiaria;

    public Carro(String modelo, String marca, String renavam, Double valorDiaria) {
        validarCamposObrigatorios(modelo, marca, renavam, valorDiaria);
        this.modelo = modelo;
        this.marca = marca;
        this.renavam = renavam;
        this.valorDiaria = valorDiaria;
    }

    private void validarCamposObrigatorios(String modelo, String marca, String renavam, Double valorDiaria) {
        ExcecaoDeDominio.quandoTextoVazioOuNulo(modelo, "Não é possível criar um carro sem modelo.");
        ExcecaoDeDominio.quandoTextoVazioOuNulo(marca, "Não é possível criar um carro sem marca.");
        ExcecaoDeDominio.quandoTextoVazioOuNulo(renavam, "Não é possível criar um carro sem renavam.");
        ExcecaoDeDominio.quandoTamanoDiferenteDeOnze(renavam, "O código renavam deve ter 11 digitos.");
        ExcecaoDeDominio.quandoValorMenorIgualZero(valorDiaria, "Não é possível criar um carro com valor da diária menor ou igual a zero.");
    }
}
