package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.model.Veiculo;
import lombok.Getter;

@Getter
public class VeiculoDto {

    private Long idVeiculo;
    private ApartamentoDto apartamento;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;

    public VeiculoDto(Veiculo veiculo) {
        EntityToDto entityToDto = new EntityToDto();
        this.apartamento = entityToDto.converter(veiculo.getApartamento());
        this.idVeiculo = veiculo.getIdVeiculo();
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.cor = veiculo.getCor();
    }
}
