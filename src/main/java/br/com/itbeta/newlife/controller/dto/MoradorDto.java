package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Morador;
import lombok.Getter;

@Getter
public class MoradorDto {

    private Long idMorador;
    private ApartamentoDto apartamento;
    private String email;
    private String nomeContatoEmergencial;
    private String telefoneContatoEmergencial;
    private String nome;
    private String rg;
    private String cpf;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String observacoes;

    public MoradorDto(Morador morador) {
        EntityToDto entityToDto = new EntityToDto();
        this.apartamento = entityToDto.converter(morador.getApartamento());
        this.idMorador = morador.getIdMorador();
        this.email = morador.getEmail();
        this.nomeContatoEmergencial = morador.getNomeContatoEmergencial();
        this.telefoneContatoEmergencial = morador.getTelefoneContatoEmergencial();
        this.nome = morador.getNome();
        this.rg = morador.getRg();
        this.cpf = morador.getCpf();
        this.telefonePrincipal = morador.getTelefonePrincipal();
        this.telefoneSecundario = morador.getTelefoneSecundario();
        this.observacoes = morador.getObservacoes();
    }
}
