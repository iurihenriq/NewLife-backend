package br.com.itbeta.newlife.model;

import br.com.itbeta.newlife.controller.form.VeiculoForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVeiculo")
    private Long idVeiculo;

    @ManyToOne
    @JoinColumn(name = "apartamento")
    private Apartamento apartamento;

    @Column(name = "placa")
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "cor")
    private String cor;

    public Veiculo(VeiculoForm form) {
//        this.apartamento = form.getApartamento();
        this.placa = form.getPlaca();
        this.marca = form.getMarca();
        this.modelo = form.getModelo();
        this.cor = form.getCor();
    }

    public void update(VeiculoForm form) {
//        this.apartamento = form.getApartamento();
        this.placa = form.getPlaca();
        this.marca = form.getMarca();
        this.modelo = form.getModelo();
        this.cor = form.getCor();
    }

    public void addApartamentos(Apartamento idApto) {
        this.apartamento = idApto;
    }
}
