package com.aluguelcarro.aluguel.dominio;

import br.com.caelum.stella.validation.CPFValidator;
import com.aluguelcarro.aluguel.comun.dominio.ExcecaoDeDominio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "CLIENTE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomeCompleto;

    @Column
    private String email;

    @Column
    private String cpf;

    public Cliente(String nomeCompleto, String email, String cpf) {
        validarCamposObrigatorios(nomeCompleto, email, cpf);
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
    }

    private void validarCamposObrigatorios(String nomeCompleto, String email, String cpf) {
        ExcecaoDeDominio.quandoTextoVazioOuNulo( nomeCompleto, "Não é possível criar um cliente sem modelo.");
        ExcecaoDeDominio.quandoTextoVazioOuNulo(email, "Não é possível criar um cliente sem email.");
        ExcecaoDeDominio.quandoTextoVazioOuNulo(cpf, "Não é possível criar um cliente sem cpf.");
        validarCpf(cpf);
    }

    private void validarCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
        }catch (Exception e){
            ExcecaoDeDominio.quandoVerdadeiro(true, "Não é possível criar um cliente com cpf inválido.");
        }
    }
}
