package br.com.itbeta.newlife.model;

import br.com.itbeta.newlife.controller.form.VisitanteForm;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table (name = "visitante")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idVisitante")
    private Long idVisitante;

    @ManyToOne
    @JoinColumn (name = "apartamento")
    private Apartamento apartamento;

    @Column (name = "nome")
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

    public Visitante (VisitanteForm form) {
//        this.apartamento = form.getApartamento();
        this.nome = form.getNome();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.telefonePrincipal = form.getTelefonePrincipal();
        this.telefoneSecundario = form.getTelefoneSecundario();
        this.observacoes = form.getObservacoes();
    }

    public void update (VisitanteForm form) {
//        this.apartamento = form.getApartamento();
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
