package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.model.Apartamento;
import lombok.Getter;


import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class MoradorRow {


    public final Apartamento apartamento;

    public final MoradorRowError error;

    public final String nome;

    public final String rg;

    public final String cpf;

    public final String telefonePrincipal;

    public final String telefoneSecundario;

    public final String email;

    public final String nomeContatoEmergencial;

    public final String telefoneContatoEmergencial;

    public final String observacoes;

    public final int rowNumber;

    public final Boolean hasError;

    private MoradorRow(
            Apartamento apartamento,
            String nome,
            String rg,
            String cpf,
            String telefonePrincipal,
            String telefoneSecundario,
            String email,
            String nomeContatoEmergencial,
            String telefoneContatoEmergencial,
            String observacoes,
            int rowNumber,
            MoradorRowError error,
            Boolean hasError
    ){
        this.apartamento =apartamento;
        this.nome =nome;
        this.rg =rg;
        this.cpf =cpf;
        this.telefonePrincipal =telefonePrincipal;
        this.telefoneSecundario =telefoneSecundario;
        this.email =email;
        this.nomeContatoEmergencial =nomeContatoEmergencial;
        this.telefoneContatoEmergencial =telefoneContatoEmergencial;
        this.observacoes =observacoes;
        this.rowNumber =rowNumber;
        this.error =error;
        this.hasError =hasError;
    }

    public static MoradorRowBuilder builder(){
        return new MoradorRowBuilder();
    }

    public static class MoradorRowBuilder{
        private Boolean hasError=false;
        private MoradorRowError.MoradorRowErrorBuilder errorBuilder = MoradorRowError.builder();
        private int rowNumber;
        private Apartamento apartamento;
        private String nome;
        private String rg;
        private String cpf;
        private String telefonePrincipal;
        private String telefoneSecundario;
        private String email;
        private String nomeContatoEmergencial;
        private String telefoneContatoEmergencial;
        private String observacoes;

        public MoradorRowBuilder apartamento(Apartamento apartamento){
            this.apartamento = apartamento;
            return this;
        }

        public MoradorRowBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public MoradorRowBuilder rg(String rg){
            this.rg = rg;
            return this;
        }

        public MoradorRowBuilder cpf(String cpf){
            this.cpf = cpf;
            return this;
        }

        public MoradorRowBuilder telefonePrincipal(String telefonePrincipal){
            this.telefonePrincipal = telefonePrincipal;
            return this;
        }

        public MoradorRowBuilder telefoneSecundario(String telefoneSecundario){
            this.telefoneSecundario = telefoneSecundario;
            return this;
        }

        public MoradorRowBuilder email(String email){
            this.email = email;
            return this;
        }

        public MoradorRowBuilder nomeContatoEmergencial(String nomeContatoEmergencial){
            this.nomeContatoEmergencial = nomeContatoEmergencial;
            return this;
        }

        public MoradorRowBuilder telefoneContatoEmergencial(String telefoneContatoEmergencial){
            this.telefoneContatoEmergencial =telefoneContatoEmergencial;
            return this;
        }

        public MoradorRowBuilder observacoes(String observacoes){
            this.observacoes = observacoes;
            return this;
        }


        public MoradorRow build(){
            return new MoradorRow(
                    this.apartamento,
                    this.nome,
                    this.rg,
                    this.cpf,
                    this.telefonePrincipal,
                    this.telefoneSecundario,
                    this.email,
                    this.nomeContatoEmergencial,
                    this.telefoneContatoEmergencial,
                    this.observacoes,
                    this.rowNumber+1,
                    this.errorBuilder.build(),
                    this.hasError
            );
        }
    }
}
