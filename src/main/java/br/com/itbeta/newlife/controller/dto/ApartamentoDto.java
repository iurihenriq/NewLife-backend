package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Funcionario;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ApartamentoDto {

    private Long idApartamento;
    private int numero;

    public ApartamentoDto(Apartamento apartamento) {
        this.numero = apartamento.getNumero();
        this.idApartamento = apartamento.getIdApartamento();
    }
}
