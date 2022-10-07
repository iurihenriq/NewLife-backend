package br.com.itbeta.newlife.controller.dto;

import br.com.itbeta.newlife.exception.SheetImportException;
import br.com.itbeta.newlife.model.Apartamento;
import lombok.Getter;
import org.springframework.util.StringUtils;


import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class MoradorRow {

    public final String errorText;
    public final Apartamento apartamento;
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
            String errorText,
            Boolean hasError
    ){
        this.apartamento = apartamento;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.telefonePrincipal = telefonePrincipal;
        this.telefoneSecundario = telefoneSecundario;
        this.email = email;
        this.nomeContatoEmergencial = nomeContatoEmergencial;
        this.telefoneContatoEmergencial = telefoneContatoEmergencial;
        this.observacoes = observacoes;
        this.rowNumber = rowNumber;
        this.errorText = errorText;
        this.hasError =hasError;
    }

    public static MoradorRowBuilder builder(){
        return new MoradorRowBuilder();
    }

    public static class MoradorRowBuilder{
        private String errorText;
        private Boolean hasError=false;
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

        public MoradorRowBuilder rowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
            return this;
        }

        public MoradorRowBuilder apartamento(Apartamento apartamento){
            String notEmptyMessage = this.assertApartamentoNotEmpty(apartamento);
            this.apartamento = apartamento;
            return this;
        }

        public MoradorRowBuilder nome(String nome){
            String notEmptyMessage = this.assertStringNotEmpty(nome, "Nome");
            this.nome = nome;
            return this;
        }

        public MoradorRowBuilder rg(String rg){
            String notEmptyMessage = this.assertStringNotEmpty(rg, "RG");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.rg = rg;
                return this;
            }
            String wrongNumberField = this.validateNumberField(rg, "RG", 9);
            this.rg = rg;
            return this;
        }

        public MoradorRowBuilder cpf(String cpf){
            String notEmptyMessage = this.assertStringNotEmpty(cpf, "CPF");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.cpf = cpf;
                return this;
            }
            String wrongNumberField = this.validateNumberField(cpf, "CPF", 11);
            this.cpf = cpf;
            return this;
        }

        public MoradorRowBuilder telefonePrincipal(String telefonePrincipal){
            String notEmptyMessage = this.assertStringNotEmpty(telefonePrincipal, "Telefone Principal");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.telefonePrincipal = telefonePrincipal;
                return this;
            }
            String wrongNumberField = this.validateNumberField(telefonePrincipal, "Telefone Principal", 11);
            this.telefonePrincipal = telefonePrincipal;
            return this;
        }

        public MoradorRowBuilder telefoneSecundario(String telefoneSecundario){
            if (telefoneSecundario != null) {
                String wrongNumberField = this.validateNumberField(telefoneSecundario, "Telefone Secundário", 11);
                this.telefoneSecundario = telefoneSecundario;
                return this;
            }
            this.telefoneSecundario = telefoneSecundario;
            return this;
        }

        public MoradorRowBuilder email(String email){
            String notEmptyMessage = this.assertStringNotEmpty(email, "Email");
            if (StringUtils.hasText(notEmptyMessage)) {
                this.email = email;
                return this;
            }
            String validateEmail = this.validateEmail(email);
            errorText = validateEmail;
            this.email = email;
            return this;
        }

        public MoradorRowBuilder nomeContatoEmergencial(String nomeContatoEmergencial){
            String notEmptyMessage = this.assertStringNotEmpty(nomeContatoEmergencial, "Contato de Emergencia");
            this.nomeContatoEmergencial = nomeContatoEmergencial;
            return this;
        }

        public MoradorRowBuilder telefoneContatoEmergencial(String telefoneContatoEmergencial){
            String notEmptyMessage = this.assertStringNotEmpty(telefoneContatoEmergencial, "Telefone Emergencial");
            if (telefoneContatoEmergencial != null) {
                String wrongNumberField = this.validateNumberField(telefoneContatoEmergencial, "Telefone de Emergência", 11);
                this.telefoneContatoEmergencial = telefoneContatoEmergencial;
                return this;
            }
            this.telefoneContatoEmergencial = telefoneContatoEmergencial;
            return this;
        }

        public MoradorRowBuilder observacoes(String observacoes){
            this.observacoes = observacoes;
            return this;
        }

        private String assertStringNotEmpty(String input, String field) {
            if (!StringUtils.hasText(input)) {
                this.hasError = true;
                throw new SheetImportException("O campo " + field + " é obrigatório! Falha na linha: " + (rowNumber + 1));
            }
            return "";
        }

        private String assertApartamentoNotEmpty(Apartamento apto) {
            if (apto == null) {
                this.hasError = true;
                throw new SheetImportException("O campo Apartamento não pode ser vazio! Falha na linha: " + (rowNumber + 1));
            }
            return "";
        }

        private String validateNumberField(String input, String field, int length) {
            if (input.length() != length) {
                this.hasError = true;
                throw new SheetImportException("O campo " + field + " deve possuir " + length + " dígitos. Falha na linha: " + (rowNumber + 1));
            }
            try {
                Long.parseLong(input);
                return "";
            } catch (Exception e) {
                this.hasError = true;
                throw new SheetImportException("O campo " + field + " deve possuir apenas números. Falha na linha: " + (rowNumber + 1));
            }
        }

        private String validateEmail(String input) {
            if (input != null) {
                if (!input.contains("@")) {
                    this.hasError = true;
                    throw new SheetImportException("Insira um email válido. Falha na linha: " + (rowNumber + 1));
                } else if (input.indexOf("@") != input.lastIndexOf("@")) {
                    this.hasError = true;
                    throw new SheetImportException("O email não pode conter mais de um caractere @. Falha na linha: " + (rowNumber + 1));
                }
                return "";
            }
            this.hasError = true;
            throw new SheetImportException("O campo email não pode ser vazio. Falha na linha: " + (rowNumber + 1));
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
                    this.errorText,
                    this.hasError
            );
        }
    }
}
