package br.com.itbeta.newlife.conversor;

import br.com.itbeta.newlife.controller.dto.ApartamentoDto;
import br.com.itbeta.newlife.model.Apartamento;

public class EntityToDto {
    public ApartamentoDto converter(Apartamento apartamento) {
        return new ApartamentoDto(apartamento);
    }
}
