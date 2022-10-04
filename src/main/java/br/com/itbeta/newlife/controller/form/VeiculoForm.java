package br.com.itbeta.newlife.controller.form;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class VeiculoForm {

    private Long apartamento;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;

    public VeiculoForm(Veiculo veiculo) {
        this.apartamento = veiculo.getApartamento().getIdApartamento();
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.cor = veiculo.getCor();
    }
}
