package br.com.itbeta.newlife.controller.form;

import br.com.itbeta.newlife.controller.dto.ApartamentoDto;
import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FuncionarioForm {

    private Long apartamento;
    private String nome;
    private String rg;
    private String cpf;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String observacoes;


    public FuncionarioForm(Funcionario funcionario) {
        this.apartamento = funcionario.getApartamento().getIdApartamento();
        this.nome = funcionario.getNome();
        this.rg = funcionario.getRg();
        this.cpf = funcionario.getCpf();
        this.telefonePrincipal = funcionario.getTelefonePrincipal();
        this.telefoneSecundario = funcionario.getTelefoneSecundario();
        this.observacoes = funcionario.getObservacoes();
    }
}
