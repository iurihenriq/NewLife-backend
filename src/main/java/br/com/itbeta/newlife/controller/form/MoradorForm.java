package br.com.itbeta.newlife.controller.form;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Morador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class MoradorForm {

    private Long apartamento;
    private String email;
    private String nomeContatoEmergencial;
    private String telefoneContatoEmergencial;
    private String nome;
    private String rg;
    private String cpf;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String observacoes;

    public MoradorForm(Morador morador) {
        this.apartamento = morador.getApartamento().getIdApartamento();
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
