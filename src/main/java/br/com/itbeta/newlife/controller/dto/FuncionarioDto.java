package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Funcionario;
import lombok.Getter;

@Getter
public class FuncionarioDto {

    private Long idFuncionario;
    private ApartamentoDto apartamento;
    private String nome;
    private String rg;
    private String cpf;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String observacoes;

    public FuncionarioDto(Funcionario funcionario) {
        EntityToDto entityToDto = new EntityToDto();
        this.apartamento = entityToDto.converter(funcionario.getApartamento());
        this.idFuncionario = funcionario.getIdFuncionario();
        this.nome = funcionario.getNome();
        this.rg = funcionario.getRg();
        this.cpf = funcionario.getCpf();
        this.telefonePrincipal = funcionario.getTelefonePrincipal();
        this.telefoneSecundario = funcionario.getTelefoneSecundario();
        this.observacoes = funcionario.getObservacoes();
    }
}
