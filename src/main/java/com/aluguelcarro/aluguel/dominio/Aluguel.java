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

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    public Aluguel(LocalDate dataInicio, LocalDate dataFim, Cliente cliente, Carro carro) {
        validarCamposObrigatorios(dataInicio, dataFim, cliente, carro);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.carro = carro;
    }

    private void validarCamposObrigatorios(LocalDate dataInicio, LocalDate dataFim, Cliente cliente, Carro carro) {
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
