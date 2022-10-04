package br.com.itbeta.newlife.model;

import br.com.itbeta.newlife.controller.form.MoradorForm;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table (name = "morador")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idMorador")
    private Long idMorador;

    @ManyToOne
    @JoinColumn (name = "apartamento")
    private Apartamento apartamento;

    @Column (name = "email")
    private String email;

    @Column (name = "nomeContatoEmergencial")
    private String nomeContatoEmergencial;

    @Column (name = "telefoneContatoEmergencial")
    private String telefoneContatoEmergencial;

    @Column(name = "nome")
    private String nome;

    @Column (name = "rg")
    private String rg;

    @Column (name = "cpf")
    private String cpf;

    @Column (name = "telefonePrincipal")
    private String telefonePrincipal;

    @Column (name = "telefoneSecundario")
    private String telefoneSecundario;

    @Column (name = "observacoes")
    private String observacoes;

    public Morador (MoradorForm form) {
//        this.apartamento = form.getApartamento();
        this.email = form.getEmail();
        this.nomeContatoEmergencial = form.getNomeContatoEmergencial();
        this.telefoneContatoEmergencial = form.getTelefoneContatoEmergencial();
        this.nome = form.getNome();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.telefonePrincipal = form.getTelefonePrincipal();
        this.telefoneSecundario = form.getTelefoneSecundario();
        this.observacoes = form.getObservacoes();
    }

    public void update (MoradorForm form) {
//        this.apartamento = form.getApartamento();
        this.email = form.getEmail();
        this.nomeContatoEmergencial = form.getNomeContatoEmergencial();
        this.telefoneContatoEmergencial = form.getTelefoneContatoEmergencial();
        this.nome = form.getNome();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.telefonePrincipal = form.getTelefonePrincipal();
        this.telefoneSecundario = form.getTelefoneSecundario();
        this.observacoes = form.getObservacoes();
    }

    public void addApartamentos(Apartamento idApto){
        this.apartamento = idApto;
    }
}
