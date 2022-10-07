package br.com.itbeta.newlife.controller.form;

import br.com.itbeta.newlife.model.Visitante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VisitanteForm {

    private Long apartamento;
    private String nome;
    private String rg;
    private String cpf;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String observacoes;

    public VisitanteForm(Visitante visitante) {
        this.apartamento = visitante.getApartamento().getIdApartamento();
        this.nome = visitante.getNome();
        this.rg = visitante.getRg();
        this.cpf = visitante.getCpf();
        this.telefonePrincipal = visitante.getTelefonePrincipal();
        this.telefoneSecundario = visitante.getTelefoneSecundario();
        this.observacoes = visitante.getObservacoes();
    }
}
