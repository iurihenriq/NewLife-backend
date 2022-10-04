package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Visitante;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class VisitanteDto {

    private Long idVisitante;
    private ApartamentoDto apartamento;
    private String nome;
    private String rg;
    private String cpf;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String observacoes;

    public VisitanteDto(Visitante visitante) {
        EntityToDto entityToDto = new EntityToDto();
        this.apartamento = entityToDto.converter(visitante.getApartamento());
        this.idVisitante = visitante.getIdVisitante();
        this.nome = visitante.getNome();
        this.rg = visitante.getRg();
        this.cpf = visitante.getCpf();
        this.telefonePrincipal = visitante.getTelefonePrincipal();
        this.telefoneSecundario = visitante.getTelefoneSecundario();
        this.observacoes = visitante.getObservacoes();
    }
}

