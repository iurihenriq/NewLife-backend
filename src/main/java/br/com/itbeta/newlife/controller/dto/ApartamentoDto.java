package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.model.Apartamento;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
