package com.aluguelcarro.aluguel.dominio;

import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "ALUGUEL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @Column
    private Long clienteId;

    @Column
    private Long carroId;

    public Aluguel(LocalDate dataInicio, LocalDate dataFim, Long clienteId, Long carroId) {
        validarCamposObrigatorios(dataInicio, dataFim, clienteId, carroId);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.clienteId = clienteId;
        this.carroId = carroId;
    }

    private void validarCamposObrigatorios(LocalDate dataInicio, LocalDate dataFim, Long cliente, Long carro) {
        ExcecaoDeDominio.quandoNulo(dataInicio, "Não é possível criar um aluguel sem data de inicio.");
        ExcecaoDeDominio.quandoNulo(dataFim, "Não é possível criar um aluguel sem data final.");
        ExcecaoDeDominio.quandoNulo(cliente, "Não é possível criar um aluguel sem um cliente.");
        ExcecaoDeDominio.quandoNulo(carro, "Não é possível criar um aluguel sem um carro.");
        validarDatas(dataInicio, dataFim);
    }

    private void validarDatas(LocalDate dataInicio, LocalDate dataFim) {
        Boolean dataInicioEhMenorQueAhDataAtual = dataInicio.isBefore(LocalDate.now());

        ExcecaoDeDominio.quandoVerdadeiro(dataInicioEhMenorQueAhDataAtual,
                "Não é possível criar um aluguel com a data inicial menor que a data atual.");

        Boolean dataInicioEhMenorQueAhDataFinal = dataFim.isBefore(dataInicio);

        ExcecaoDeDominio.quandoVerdadeiro(dataInicioEhMenorQueAhDataFinal,
                "Não é possível criar um aluguel com a data inicial menor que a data atual.");
    }
}
